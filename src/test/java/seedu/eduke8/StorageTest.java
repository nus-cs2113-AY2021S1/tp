package seedu.eduke8;

import org.junit.jupiter.api.Test;
import seedu.eduke8.question.QuestionListInterface;
import seedu.eduke8.storage.Storage;
import seedu.eduke8.topic.TopicInterface;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StorageTest {
    @Test
    public void load_emptyJson_returnsNull() {
        Storage storage = new Storage("data/test/empty.json");
        ArrayList<TopicInterface> topics = storage.load();
        assertNull(topics);
    }

    @Test
    public void load_exampleJson_returnsTopicsFromJson() {
        Storage storage = new Storage("data/test/example.json");
        ArrayList<TopicInterface> topics = storage.load();

        assertEquals(topics.size(), 2);

        TopicInterface firstTopic = topics.get(0);
        assertEquals(firstTopic.getTopic(), "OOP");

        QuestionListInterface firstQuestionList = firstTopic.getQuestionList();
        assertEquals(firstQuestionList.getNumberOfQuestionsInTopic(), 2);
    }
}
