package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.option.Option;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerCommandTest extends Eduke8Test {
    @Test
    void execute_correctAnswer_markAnsweredCorrectly() throws Eduke8Exception {
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Option correctOption = (Option) question.getOptionList().find(PLACEHOLDER_OPTION_ONE_DESCRIPTION);
        correctOption.markAsCorrectAnswer();

        Command answerCommand = new AnswerCommand(correctOption, question);
        answerCommand.execute(question.getOptionList(), new Ui());

        assertTrue(question.wasAnsweredCorrectly());
    }

    @Test
    void execute_wrongAnswer_notMarkAnsweredCorrectly() throws Eduke8Exception {
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Option wrongOption = (Option) question.getOptionList().find(PLACEHOLDER_OPTION_ONE_DESCRIPTION);

        Command answerCommand = new AnswerCommand(wrongOption, question);
        answerCommand.execute(question.getOptionList(), new Ui());

        assertFalse(question.wasAnsweredCorrectly());
    }
}
