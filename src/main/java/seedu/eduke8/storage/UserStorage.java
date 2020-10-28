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
            topics.add(parseToTopicJson(topicObject));
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
            parseFromTopicJson((JSONObject) topic);
        }

        return topicList.getInnerList();
    }

    private void parseFromTopicJson(JSONObject topic) throws Eduke8Exception {
        String topicDescription = (String) topic.get("topic");
        Topic topicObject = (Topic) topicList.find(topicDescription);

        JSONArray questions = (JSONArray) topic.get("questions");
        loadQuestionAttributes(questions, topicObject);

        JSONArray notes = (JSONArray) topic.get("notes");
        loadNotes(notes, topicObject);
    }

    private void loadNotes(JSONArray notes, Topic topicObject) throws Eduke8Exception {
        NoteList noteList = topicObject.getNoteList();
        for (Object note: notes) {
            parseFromNoteJson((JSONObject) note, noteList);
        }
    }

    private void parseFromNoteJson(JSONObject note, NoteList noteList) throws Eduke8Exception {
        String noteDescription = (String) note.get("description");
        String text = (String) note.get("text");
        Note noteObject = new Note(noteDescription, text);

        noteList.add(noteObject);
    }

    private void loadQuestionAttributes(JSONArray questions, Topic topicObject) throws Eduke8Exception {
        QuestionList questionList = topicObject.getQuestionList();
        for (Object question: questions) {
            parseFromQuestionJson(questionList, (JSONObject) question);
        }
    }

    private void parseFromQuestionJson(QuestionList questionList, JSONObject question) throws Eduke8Exception {
        String questionDescription = (String) question.get("description");
        Question questionObject = (Question) questionList.find(questionDescription);
        questionObject.markAsShown();
        if ((boolean) question.get("correct")) {
            questionObject.markAsAnsweredCorrectly();
        }
        if ((boolean) question.get("bookmarked")) {
            bookmarkList.add(questionObject);
        }
        if ((boolean) question.get("hint")) {
            questionObject.getHint().markAsShown();
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject parseToTopicJson(Displayable topicObject) {
        JSONObject topic = new JSONObject();
        topic.put("topic", topicObject.getDescription());

        JSONArray questions = getQuestionsJsonArray((Topic) topicObject);
        topic.put("questions", questions);

        JSONArray notes = getNotesJsonArray(((Topic) topicObject).getNoteList());
        topic.put("notes", notes);
        return topic;
    }

    @SuppressWarnings("unchecked")
    private JSONArray getNotesJsonArray(NoteList noteList) {
        JSONArray notes = new JSONArray();
        for (Displayable noteObject: noteList.getInnerList()) {
            JSONObject note = parseToNoteJson((Note) noteObject);
            notes.add(note);
        }
        return notes;
    }

    private JSONObject parseToNoteJson(Note noteObject) {
        JSONObject note = new JSONObject();

        note.put("description", noteObject.getDescription());
        note.put("text", noteObject.getNoteText());

        return note;
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
