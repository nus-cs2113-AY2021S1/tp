package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.hint.Hint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionTest extends Eduke8Test {

    private static final String PLACEHOLDER_QUESTION_DESCRIPTION = "This is a question description.";
    private static final String PLACEHOLDER_HINT_DESCRIPTION = "Please check the textbook page 88";
    private static final String PLACEHOLDER_EXPLANATION_DESCRIPTION = "Option A gives the best explanation out of all.";
    private static final int OPTIONLIST_OPTIONS_COUNT = 2;


    @Test
    void getDescription_placeholderQuestionDescription_returnsQuestionDescription() {
        //Creates a question object with description, 2 options, explanation and a hint
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);

        assertEquals(PLACEHOLDER_QUESTION_DESCRIPTION, question.getDescription());
    }

    @Test
    void wasShown_questionThatWasShownToUser_expectsTrue() {
        //Creates a question object with description, 2 options, explanation and a hint
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);

        // When question is being shown to user, it is marked as shown automatically.
        question.markAsShown();
        assertTrue(question.wasShown());
    }

    @Test
    void getOptionList_optionListWithTwoOptions_returnsCountOfTwoOptions() {
        //Creates a question object with description, 2 options, explanation and a hint
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);

        assertEquals(OPTIONLIST_OPTIONS_COUNT, question.getOptionList().getCount());
    }

    @Test
    void getHint_hintObject_returnsHintDescription() {
        //Creates a question object with description, 2 options, explanation and a hint
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);

        assertEquals(PLACEHOLDER_HINT_DESCRIPTION, question.getHint().getDescription());
    }

    @Test
    void wasHintShown_questionWithHintShown_expectsTrue() {
        //Creates a question object with description, 2 options, explanation and a hint
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);

        Hint hintThatWasShownToUser = question.getHint();

        // Shows hint to user to mark hint as shown to user before
        hintThatWasShownToUser.getDescription();

        assertTrue(question.wasHintShown());
    }

    @Test
    void getExplanation_explanationObject_returnsExplanationDescription() {
        //Creates a question object with description, 2 options, explanation and a hint
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);

        assertEquals(PLACEHOLDER_EXPLANATION_DESCRIPTION, question.getExplanation().getDescription());
    }

    @Test
    void wasAnsweredCorrectly_questionMarkedAsAnsweredCorrectly_expectsTrue() {
        //Creates a question object with description, 2 options, explanation and a hint
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);

        question.markAsAnsweredCorrectly();
        assertTrue(question.wasAnsweredCorrectly());
    }
}
