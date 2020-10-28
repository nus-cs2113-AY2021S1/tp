package seedu.eduke8.storage;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TopicsStorageTest extends Eduke8Test {
    public static final String DATA_TEST_TOPICS_JSON = "data/test/topics.json";

    @Test
    public void load_invalidPath_expectIoException() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_INVALID_PATH);
        assertThrows(IOException.class, topicsStorage::load);
    }

    @Test
    public void load_emptyJson_expectParseException() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_EMPTY_JSON);
        assertThrows(ParseException.class, topicsStorage::load);
    }

    @Test
    public void load_wrongFormatJson_expectClassCastException() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_WRONG_FORMAT_JSON);
        assertThrows(ClassCastException.class, topicsStorage::load);
    }

    @Test
    public void load_missingKeyJson_expectNullPointerException() {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_MISSING_KEY_JSON);
        assertThrows(NullPointerException.class, topicsStorage::load);
    }

    @Test
    public void load_exampleJson_returnsTopicsFromJson() throws IOException, ParseException {
        TopicsStorage topicsStorage = new TopicsStorage(DATA_TEST_TOPICS_JSON);

        ArrayList<Displayable> topics = topicsStorage.load();
        assertEquals(topics.size(), 2);

        Topic firstTopic = (Topic) topics.get(0);
        assertEquals(firstTopic.getDescription(), PLACEHOLDER_TOPIC_ONE_DESCRIPTION);

        QuestionList firstQuestionList = firstTopic.getQuestionList();
        assertEquals(firstQuestionList.getCount(), 2);
    }
}
