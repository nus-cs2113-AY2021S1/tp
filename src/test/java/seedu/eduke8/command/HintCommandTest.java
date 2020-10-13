package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HintCommandTest {
    @Test
    public void execute_normal_hintWasShown() {
        Hint hint = new Hint("test");
        Command hintCommand = new HintCommand(hint);
        hintCommand.execute(new OptionList(), new Ui());

        assertTrue(hint.wasShown());
    }
}
