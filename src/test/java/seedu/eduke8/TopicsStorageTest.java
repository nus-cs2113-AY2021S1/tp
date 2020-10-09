package seedu.eduke8;

import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.storage.TopicsStorage;
import seedu.eduke8.topic.Topic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TopicsStorageTest {
    @Test
    public void load_emptyJson_returnsNull() {
        TopicsStorage topicsStorage = new TopicsStorage("data/test/empty.json");
        ArrayList<Displayable> topics = topicsStorage.load();
        assertNull(topics);
    }

    @Test
    public void load_exampleJson_returnsTopicsFromJson() {
        TopicsStorage topicsStorage = new TopicsStorage("data/test/example.json");
        ArrayList<Displayable> topics = topicsStorage.load();

        assertEquals(topics.size(), 2);

        Topic firstTopic = (Topic) topics.get(0);
        assertEquals(firstTopic.getDescription(), "OOP");

        QuestionList firstQuestionList = firstTopic.getQuestionList();
        assertEquals(firstQuestionList.getNumberOfQuestionsInTopic(), 2);
    }
}
