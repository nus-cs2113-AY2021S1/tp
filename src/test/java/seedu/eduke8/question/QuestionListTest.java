package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.topic.TopicList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionListTest extends Eduke8Test {

    private static final int DEFAULT_QUESTION_COUNT = 2;

    @Test
    void getCount_twoQuestions_returnsCountOfTwo() {
        QuestionList questionList = createQuestionListWithTwoUniqueQuestions();

        assertEquals(DEFAULT_QUESTION_COUNT, questionList.getCount());
    }

    @Test
    void find_questionListWithTwoQuestions_returnsFirstQuestion() throws Eduke8Exception {
        QuestionList questionList = createQuestionListWithTwoUniqueQuestions();

        Displayable firstQuestionOfQuestionList = questionList.find(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);

        assertEquals(PLACEHOLDER_QUESTION_ONE_DESCRIPTION, firstQuestionOfQuestionList.getDescription());
    }

    @Test
    void find_questionNotFound_expectEduke8Exception() {
        QuestionList questionList = createQuestionListWithTwoUniqueQuestions();

        assertThrows(Eduke8Exception.class, () -> questionList.find(NONSENSE_DESCRIPTION));
    }
}