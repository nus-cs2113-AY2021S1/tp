package seedu.eduke8.storage;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserStorageTest extends Eduke8Test {
    private static final String DATA_TEST_USER_JSON = "data/test/user.json";
    private static final String DATA_TEST_SAVE_JSON = "data/test/save.json";
    private final TopicList topicList;
    private final BookmarkList bookmarkList;

    UserStorageTest() throws Eduke8Exception {
        topicList = createTestTopicList();
        Topic topic = (Topic) topicList.find(PLACEHOLDER_TOPIC_ONE_DESCRIPTION);
        QuestionList questionList = topic.getQuestionList();
        bookmarkList = new BookmarkList();
        Question questionOne = (Question) questionList.find(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        questionOne.markAsShown();
        bookmarkList.add(questionOne);
        questionOne.markAsAnsweredCorrectly();
        questionOne.getHint().markAsShown();
        Displayable questionTwo = questionList.find(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        questionTwo.markAsShown();
    }

    @Test
    void save_newPath_returnsCreatedFile() throws IOException {
        UserStorage userStorage = new UserStorage(DATA_TEST_SAVE_JSON, topicList, bookmarkList);
        File returnedFile = userStorage.save();
        assertTrue(returnedFile.exists());

        File correctFile = new File(getSaveTestPath());

        assertEquals(returnedFile, correctFile);
    }

    @Test
    void load_invalidPath_returnsEmptyArrayList() throws ParseException, Eduke8Exception, IOException {
        UserStorage userStorage = new UserStorage(DATA_TEST_INVALID_PATH, topicList, bookmarkList);
        ArrayList<Displayable> topics = userStorage.load();
        assertEquals(topics.size(), 0);
    }

    @Test
    void load_emptyJson_expectParseException() {
        UserStorage userStorage = new UserStorage(DATA_TEST_EMPTY_JSON, topicList, bookmarkList);
        assertThrows(ParseException.class, userStorage::load);
    }

    @Test
    void load_wrongFormatJson_expectClassCastException() {
        UserStorage userStorage = new UserStorage(DATA_TEST_WRONG_FORMAT_JSON, topicList, bookmarkList);
        assertThrows(ClassCastException.class, userStorage::load);
    }

    @Test
    void load_missingKeyJson_expectNullPointerException() {
        UserStorage userStorage = new UserStorage(DATA_TEST_MISSING_KEY_JSON, topicList, bookmarkList);
        assertThrows(NullPointerException.class, userStorage::load);
    }

    @Test
    void load_exampleJson_returnsUserAttributesFromJson() throws ParseException, Eduke8Exception, IOException {
        TopicList newTopicList = createTestTopicList();
        BookmarkList newBookmarkList = new BookmarkList();
        UserStorage userStorage = new UserStorage(DATA_TEST_USER_JSON, newTopicList, newBookmarkList);

        userStorage.load();
        Topic topic = (Topic) newTopicList.find(PLACEHOLDER_TOPIC_ONE_DESCRIPTION);

        Question questionOne = (Question) topic.getQuestionList().find(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        assertTrue(questionOne.wasAnsweredCorrectly());
        assertTrue(questionOne.wasHintShown());
        assertNotNull(newBookmarkList.find(PLACEHOLDER_QUESTION_ONE_DESCRIPTION));

        Question questionTwo = (Question) topic.getQuestionList().find(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);
        assertFalse(questionTwo.wasAnsweredCorrectly());
        assertFalse(questionTwo.wasHintShown());
        assertNull(newBookmarkList.find(PLACEHOLDER_QUESTION_TWO_DESCRIPTION));
    }

    String getSaveTestPath() {
        String fullPath = new File("").getAbsolutePath();
        String[] relativePathSplit = UserStorageTest.DATA_TEST_SAVE_JSON.split("/");
        for (String path: relativePathSplit) {
            fullPath += File.separator + path;
        }

        return fullPath;
    }
}
