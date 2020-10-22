package seedu.eduke8.topic;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.question.QuestionList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopicTest extends Eduke8Test {
    private static final String INPUT = "JUnit: Basic";

    @Test
    void getsTopicDescription_topicDescription_returnsTopicDescription() throws Eduke8Exception {
        Topic topic = new Topic(INPUT, createQuestionList());

        assertEquals(INPUT, topic.getDescription());
    }

    @Test
    void getsQuestionList_questionList_returnsQuestionList() throws Eduke8Exception {
        QuestionList questionList = createQuestionList();
        Topic topic = new Topic(INPUT, questionList);

        assertEquals(questionList, topic.getQuestionList());
    }
}
