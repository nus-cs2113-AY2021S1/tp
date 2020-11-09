package seedu.eduke8.topic;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.question.QuestionList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopicTest extends Eduke8Test {
    private static final String INPUT = "JUnit: Basic";

    private Topic topic;
    private QuestionList questionList;

    TopicTest() {
        questionList = createTestQuestionList();
        topic = new Topic(INPUT, questionList);
    }

    @Test
    void getsTopicDescription_topicDescription_returnsTopicDescription() {
        assertEquals(INPUT, topic.getDescription());
    }

    @Test
    void getsQuestionList_questionList_returnsQuestionList() {
        assertEquals(questionList, topic.getQuestionList());
    }
}
