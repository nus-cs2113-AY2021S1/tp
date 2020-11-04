package seedu.eduke8.storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.note.Note;
import seedu.eduke8.note.NoteList;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.stats.TopicalStatsCalculator;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class UserStorage extends LocalStorage {
    public static final String KEY_TEXT = "text";
    private BookmarkList bookmarkList;
    private TopicList topicList;

    public UserStorage(String filePath, TopicList topicList, BookmarkList bookmarkList) {
        super(filePath);
        this.topicList = topicList;
        this.bookmarkList = bookmarkList;
    }

    /**
     * Saves user attributes to a local file in JSON format from the topicList and bookmarkList.
     * Returns the file where data was written to.
     *
     * @return File where data was written to.
     * @throws IOException If the file is not found or cannot be written to.
     */
    @Override
    @SuppressWarnings("unchecked")
    public File save() throws IOException {
        file = super.save();

        assert file.exists();

        // Get all the questions seen before
        JSONArray topics = new JSONArray();

        for (Displayable topicObject : topicList.getInnerList()) {
            topics.add(parseToTopicJson(topicObject));
        }

        //Write JSON file
        FileWriter writer = new FileWriter(file.getAbsolutePath());
        writer.write(topics.toJSONString());
        writer.flush();

        LOGGER.log(Level.INFO, "User data saved to file");

        return file;
    }

    /**
     * Loads user attributes from a local file in JSON format into the topicList and bookmarkList.
     * Returns the topics in the topicList.
     *
     * @return Topics from the topicList in an ArrayList.
     * @throws IOException          If the file is not found or cannot be read.
     * @throws ParseException       If the file contents cannot be parsed as a JSON.
     * @throws ClassCastException   If the the nesting of arrays and objects in the JSON is wrong
     * @throws NullPointerException If the keys required are not present in the file.
     */
    @Override
    public ArrayList<Displayable> load()
            throws IOException, ParseException, Eduke8Exception, ClassCastException, NullPointerException {
        if (!file.exists()) {
            return super.load();
        }
        assert file.exists();

        JSONArray topicsAsJsonArray = getJsonArrayFromFile();

        for (Object topic : topicsAsJsonArray) {
            parseFromTopicJson((JSONObject) topic);
        }

        LOGGER.log(Level.INFO, "User data loaded from file");

        return topicList.getInnerList();
    }


    private void parseFromTopicJson(JSONObject topic) throws Eduke8Exception {
        String topicDescription = (String) topic.get(KEY_TOPIC);
        Topic topicObject = (Topic) topicList.find(topicDescription);

        JSONArray questions = (JSONArray) topic.get(KEY_QUESTIONS);
        loadQuestionAttributes(questions, topicObject);

        JSONArray notes = (JSONArray) topic.get(KEY_NOTES);
        loadNotes(notes, topicObject);
    }

    private void loadNotes(JSONArray notes, Topic topicObject) throws Eduke8Exception {
        NoteList noteList = topicObject.getNoteList();
        for (Object note : notes) {
            parseFromNoteJson((JSONObject) note, noteList);
        }
    }

    private void parseFromNoteJson(JSONObject note, NoteList noteList) throws Eduke8Exception {
        String noteDescription = (String) note.get(KEY_DESCRIPTION);
        String text = (String) note.get(KEY_TEXT);
        Note noteObject = new Note(noteDescription, text);

        noteList.add(noteObject);
    }

    private void loadQuestionAttributes(JSONArray questions, Topic topicObject) throws Eduke8Exception {
        QuestionList questionList = topicObject.getQuestionList();
        for (Object question : questions) {
            parseFromQuestionJson(questionList, (JSONObject) question);
        }
    }

    private void parseFromQuestionJson(QuestionList questionList, JSONObject question) throws Eduke8Exception {
        String questionDescription = (String) question.get(KEY_DESCRIPTION);
        Question questionObject = (Question) questionList.find(questionDescription);
        questionObject.markAsShown();
        if ((boolean) question.get(KEY_CORRECT)) {
            questionObject.markAsAnsweredCorrectly();
        }
        if ((boolean) question.get(KEY_BOOKMARKED)) {
            bookmarkList.add(questionObject);
        }
        if ((boolean) question.get(KEY_HINT)) {
            questionObject.getHint().markAsShown();
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject parseToTopicJson(Displayable topicObject) {
        JSONObject topic = new JSONObject();
        topic.put(KEY_TOPIC, topicObject.getDescription());

        JSONArray questions = getQuestionsJsonArray((Topic) topicObject);
        topic.put(KEY_QUESTIONS, questions);

        JSONArray notes = getNotesJsonArray(((Topic) topicObject).getNoteList());
        topic.put(KEY_NOTES, notes);
        return topic;
    }

    @SuppressWarnings("unchecked")
    private JSONArray getNotesJsonArray(NoteList noteList) {
        JSONArray notes = new JSONArray();
        for (Displayable noteObject : noteList.getInnerList()) {
            JSONObject note = parseToNoteJson((Note) noteObject);
            notes.add(note);
        }
        return notes;
    }

    @SuppressWarnings("unchecked")
    private JSONObject parseToNoteJson(Note noteObject) {
        JSONObject note = new JSONObject();

        note.put(KEY_DESCRIPTION, noteObject.getDescription());
        note.put(KEY_TEXT, noteObject.getNoteText());

        return note;
    }


    @SuppressWarnings("unchecked")
    private JSONArray getQuestionsJsonArray(Topic topicObject) {
        JSONArray questions = new JSONArray();
        TopicalStatsCalculator topicalStatsCalculator = new TopicalStatsCalculator(topicObject);
        ArrayList<Displayable> attemptedQuestions = topicalStatsCalculator.getTopicalAttemptedQuestions();
        for (Displayable questionObject : attemptedQuestions) {
            JSONObject question = parseToQuestionJson((Question) questionObject);
            questions.add(question);
        }
        return questions;
    }

    @SuppressWarnings("unchecked")
    private JSONObject parseToQuestionJson(Question questionObject) {
        JSONObject question = new JSONObject();

        question.put(KEY_DESCRIPTION, questionObject.getDescription());
        question.put(KEY_CORRECT, questionObject.wasAnsweredCorrectly());
        question.put(KEY_BOOKMARKED, bookmarkList.find(questionObject.getDescription()) != null);
        question.put(KEY_HINT, questionObject.wasHintShown());

        return question;
    }


}
