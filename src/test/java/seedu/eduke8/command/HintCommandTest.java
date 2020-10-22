package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HintCommandTest extends Eduke8Test {
    @Test
    public void execute_normal_hintWasShown() {
        Hint hint = new Hint(PLACEHOLDER_HINT_DESCRIPTION);
        Command hintCommand = new HintCommand(hint);

        OptionList optionList = createTestOptionList();

        hintCommand.execute(optionList, new Ui());

        assertTrue(hint.wasShown());
    }
}
