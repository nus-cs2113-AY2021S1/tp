package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class IncompleteCommandTest extends Eduke8Test {

    @Test
    void execute_correctAnswer_notMarkAnsweredCorrectly() {
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);

        Command incompleteCommand = new IncompleteCommand(question, 1);
        incompleteCommand.execute(question.getOptionList(), new Ui());

        assertFalse(question.wasAnsweredCorrectly());
    }
}
