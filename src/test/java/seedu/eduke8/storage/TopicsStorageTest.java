package seedu.eduke8.storage;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.TopicQuestionList;
import seedu.eduke8.topic.Topic;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TopicsStorageTest {
    @Test
    public void load_invalidPath_expectIoException() {
        TopicsStorage topicsStorage = new TopicsStorage("data/test/invalid.json");
        assertThrows(IOException.class, topicsStorage::load);
    }

    @Test
    public void load_emptyJson_expectParseException() {
        TopicsStorage topicsStorage = new TopicsStorage("data/test/empty.json");
        assertThrows(ParseException.class, topicsStorage::load);
    }

    @Test
    public void load_exampleJson_returnsTopicsFromJson() {
        TopicsStorage topicsStorage = new TopicsStorage("data/test/example.json");

        try {
            ArrayList<Displayable> topics = topicsStorage.load();
            assertEquals(topics.size(), 2);

            Topic firstTopic = (Topic) topics.get(0);
            assertEquals(firstTopic.getDescription(), "OOP");

            TopicQuestionList firstTopicQuestionList = firstTopic.getQuestionList();
            assertEquals(firstTopicQuestionList.getCount(), 2);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }


    }
}
