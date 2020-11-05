package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.Displayable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionListTest extends Eduke8Test {
    private static final int DEFAULT_QUESTION_COUNT = 3;

    private QuestionList questionList;

    QuestionListTest() {
        questionList = createTestQuestionList();
    }

    @Test
    void questionListConstructor_nullQuestionsArgument_expectsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new QuestionList(null);
        });
    }

    @Test
    void getCount_threeQuestions_returnsCountOfThree() {
        assertEquals(DEFAULT_QUESTION_COUNT, questionList.getCount());
    }

    @Test
    void find_questionListWithThreeQuestions_returnsFirstQuestion() {
        Displayable firstQuestionOfQuestionList = questionList.find(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);

        assertEquals(PLACEHOLDER_QUESTION_ONE_DESCRIPTION, firstQuestionOfQuestionList.getDescription());
    }

    @Test
    void find_questionNotFound_returnsNull() {
        assertNull(questionList.find(NONSENSE_DESCRIPTION));
    }
}