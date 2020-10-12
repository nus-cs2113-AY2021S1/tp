package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionTest {

    private static final String PLACEHOLDER_QUESTION_DESCRIPTION = "This is a question description.";
    private static final String PLACEHOLDER_HINT_DESCRIPTION = "Please check the textbook page 88";
    private static final int OPTIONLIST_OPTIONS_COUNT = 2;


    @Test
    void getDescription_placeholderQuestionDescription_returnsQuestionDescription() {
        // Creates a question object with description, 2 options and a hint
        Question question = createTestQuestion();

        assertEquals(PLACEHOLDER_QUESTION_DESCRIPTION, question.getDescription());
    }

    @Test
    void wasShown_questionThatWasShownToUser_expectsTrue() {
        // Creates a question object with description, 2 options and a hint
        Question question = createTestQuestion();

        // When question is being shown to user, it is marked as shown automatically.
        String questionDescriptionShownToUser = question.getDescription();
        assertTrue(question.wasShown());
    }

    @Test
    void getOptionList_optionListWithTwoOptions_returnsCountOfTwoOptions() {
        // Creates a question object with description, 2 options and a hint
        Question question = createTestQuestion();

        assertEquals(OPTIONLIST_OPTIONS_COUNT, question.getOptionList().getCount());
    }

    @Test
    void getHint_hintObject_returnsHintDescription() {
        // Creates a question object with description, 2 options and a hint
        Question question = createTestQuestion();

        assertEquals(PLACEHOLDER_HINT_DESCRIPTION, question.getHint().getDescription());
    }


    @Test
    void wasHintShown_questionWithHintShown_expectsTrue() {
        // Creates a question object with description, 2 options and a hint
        Question question = createTestQuestion();

        Hint hintThatWasShownToUser = question.getHint();

        // Shows hint to user to mark hint as shown to user before
        hintThatWasShownToUser.getDescription();

        assertTrue(question.wasHintShown());
    }


    @Test
    void wasAnsweredCorrectly_questionMarkedAsAnsweredCorrectly_expectsTrue() {
        // Creates a question object with description, 2 options and a hint
        Question question = createTestQuestion();

        question.markAsAnsweredCorrectly();
        assertTrue(question.wasAnsweredCorrectly());
    }


    // Creates a standard question for the tests with description, 2 options and a hint
    private Question createTestQuestion() {
        String inputQuestionDescription = PLACEHOLDER_QUESTION_DESCRIPTION;

        Option option1 = new Option("test1");
        Option option2 = new Option("test2");
        OptionList optionList = new OptionList();
        optionList.add(option1);
        optionList.add(option2);

        String inputHintDescription = PLACEHOLDER_HINT_DESCRIPTION;
        Hint hint = new Hint(inputHintDescription);

        Question question = new Question(inputQuestionDescription, optionList, hint);

        return question;
    }
}