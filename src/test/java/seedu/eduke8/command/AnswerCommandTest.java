package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerCommandTest {
    @Test
    public void execute_correctAnswer_markAnsweredCorrectly() {
        Option option = new Option("test");
        option.markAsCorrectAnswer();
        OptionList optionList = new OptionList();
        optionList.add(option);

        Hint hint = new Hint("test");

        Question question = new Question("test", optionList, hint);

        Command answerCommand = new AnswerCommand(option, question);
        answerCommand.execute(optionList, new Ui());

        assertTrue(question.wasAnsweredCorrectly());
    }
}
