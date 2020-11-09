package seedu.eduke8.storage;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TopicsStorageTest extends Eduke8Test {
    public static final String DATA_TEST_TWO_CORRECT_ANSWER_JSON = "data/test/two_correct_answer.json";
    public static final String DATA_TEST_THREE_OPTIONS_JSON = "data/test/three_options.json";
    private static final String DATA_TEST_TOPICS_JSON = "data/test/topics.json";
    public static final String DATA_TEST_NO_CORRECT_ANSWER_JSON = "data/test/no_correct_answer.json";
    public static final String DATA_TEST_FIVE_OPTIONS_JSON = "data/test/five_options.json";
    public static final String DATA_TEST_DUPLICATE_TOPICS_JSON = "data/test/duplicate_topics.json";
    public static final String DATA_TEST_BLANK_TOPIC_TITLE_JSON = "data/test/blank_topic_title.json";
    public static final String DATA_TEST_BLANK_QUESTION_DESCRIPTION_JSON = "data/test/blank_question_description.json";

    @Test
    void load_invalidPath_expectNoError() throws ParseException, Eduke8Exception, IOException {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_INVALID_PATH);
        topicsStorage.load();
        assertTrue(true); // Should fall back on file in resources
    }

    @Test
    void load_emptyJson_expectParseException() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_EMPTY_JSON);
        assertThrows(ParseException.class, topicsStorage::load);
    }

    @Test
    void load_wrongFormatJson_expectClassCastException() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_WRONG_FORMAT_JSON);
        assertThrows(ClassCastException.class, topicsStorage::load);
    }

    @Test
    void load_missingKeyJson_expectNullPointerException() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_MISSING_KEY_JSON);
        assertThrows(NullPointerException.class, topicsStorage::load);
    }

    @Test
    void load_noCorrectAnswerJson_expectEduke8Exception() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_NO_CORRECT_ANSWER_JSON);
        assertThrows(Eduke8Exception.class, topicsStorage::load);
    }

    @Test
    void load_twoCorrectAnswerJson_expectEduke8Exception() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_TWO_CORRECT_ANSWER_JSON);
        assertThrows(Eduke8Exception.class, topicsStorage::load);
    }

    @Test
    void load_threeOptionsJson_expectEduke8Exception() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_THREE_OPTIONS_JSON);
        assertThrows(Eduke8Exception.class, topicsStorage::load);
    }

    @Test
    void load_fiveOptionsJson_expectEduke8Exception() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_FIVE_OPTIONS_JSON);
        assertThrows(Eduke8Exception.class, topicsStorage::load);
    }

    @Test
    void load_duplicateTopicsJson_expectEduke8Exception() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_DUPLICATE_TOPICS_JSON);
        assertThrows(Eduke8Exception.class, topicsStorage::load);
    }

    @Test
    void load_blankTopicTitleJson_expectEduke8Exception() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_BLANK_TOPIC_TITLE_JSON);
        assertThrows(Eduke8Exception.class, topicsStorage::load);
    }

    @Test
    void load_blankQuestionDescriptionJson_expectEduke8Exception() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_BLANK_QUESTION_DESCRIPTION_JSON);
        assertThrows(Eduke8Exception.class, topicsStorage::load);
    }

    @Test
    void load_exampleJson_returnsTopicsFromJson() throws IOException, ParseException, Eduke8Exception {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_TOPICS_JSON);

        ArrayList<Displayable> topics = topicsStorage.load();
        assertEquals(topics.size(), 2);

        Topic firstTopic = (Topic) topics.get(0);
        assertEquals(firstTopic.getDescription(), PLACEHOLDER_TOPIC_ONE_DESCRIPTION);

        QuestionList firstQuestionList = firstTopic.getQuestionList();
        assertEquals(firstQuestionList.getCount(), 2);
    }
}
