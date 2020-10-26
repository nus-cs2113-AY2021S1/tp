package seedu.eduke8.storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.note.Note;
import seedu.eduke8.note.NoteList;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.stats.TopicalStatsCalculator;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserStorage extends LocalStorage {
    private BookmarkList bookmarkList;
    private TopicList topicList;

    public UserStorage(String filePath, TopicList topicList, BookmarkList bookmarkList) {
        super(filePath);
        this.topicList = topicList;
        this.bookmarkList = bookmarkList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public File save() throws IOException {
        file = super.save();

        // Get all the questions seen before
        JSONArray topics = new JSONArray();

        for (Displayable topicObject: topicList.getInnerList()) {
            JSONObject topic = new JSONObject();
            topic.put("topic", topicObject.getDescription());

            JSONArray questions = getQuestionsJsonArray((Topic) topicObject);
            topic.put("questions", questions);

            JSONArray notes = getNotesJsonArray(((Topic) topicObject).getNoteList());
            topic.put("notes", notes);

            topics.add(topic);
        }

        //Write JSON file
        FileWriter writer = new FileWriter(file.getAbsolutePath());
        writer.write(topics.toJSONString());
        writer.flush();

        return file;
    }

    @Override
    public ArrayList<Displayable> load() throws IOException, ParseException, Eduke8Exception {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray topicsAsJsonArray = getJsonArrayFromFile();
        for (Object topic: topicsAsJsonArray) {
            String topicDescription = (String) ((JSONObject) topic).get("topic");
            Topic topicObject = (Topic) topicList.find(topicDescription);

            QuestionList questionList = topicObject.getQuestionList();
            JSONArray questions = (JSONArray) ((JSONObject) topic).get("questions");
            for (Object question: questions) {
                String questionDescription = (String) ((JSONObject) question).get("description");
                Question questionObject = (Question) questionList.find(questionDescription);
                questionObject.markAsShown();
                if ((boolean) ((JSONObject) question).get("correct")) {
                    questionObject.markAsAnsweredCorrectly();
                }
                if ((boolean) ((JSONObject) question).get("bookmarked")) {
                    bookmarkList.add(questionObject);
                }
                if ((boolean) ((JSONObject) question).get("hint")) {
                    questionObject.getHint().markAsShown();
                }
            }

            NoteList noteList = topicObject.getNoteList();
            JSONArray notes = (JSONArray) ((JSONObject) topic).get("notes");
            for (Object note: notes) {
                noteList.add((Note) note);
            }
        }

        return topicList.getInnerList();
    }

    @SuppressWarnings("unchecked")
    private JSONArray getNotesJsonArray(NoteList noteList) {
        JSONArray notes = new JSONArray();
        for (Displayable noteObject: noteList.getInnerList()) {
            notes.add(noteObject.getDescription());
        }
        return notes;
    }

    @SuppressWarnings("unchecked")
    private JSONArray getQuestionsJsonArray(Topic topicObject) {
        JSONArray questions = new JSONArray();
        TopicalStatsCalculator topicalStatsCalculator = new TopicalStatsCalculator(topicObject);
        ArrayList<Displayable> attemptedQuestions = topicalStatsCalculator.getTopicalAttemptedQuestions();
        for (Displayable questionObject: attemptedQuestions) {
            JSONObject question = parseToQuestionJson((Question) questionObject);
            questions.add(question);
        }
        return questions;
    }

    @SuppressWarnings("unchecked")
    private JSONObject parseToQuestionJson(Question questionObject) {
        JSONObject question = new JSONObject();

        question.put("description", questionObject.getDescription());
        question.put("correct", questionObject.wasAnsweredCorrectly());
        question.put("bookmarked", bookmarkList.find(questionObject.getDescription()) != null);
        question.put("hint", questionObject.wasHintShown());

        return question;
    }


}
