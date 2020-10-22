package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest extends Eduke8Test {

    @Test
    public void execute_normal_isExitIsTrue() {
        Command exitCommand = new ExitCommand();
        DisplayableList displayableList = createTestOptionList();
        exitCommand.execute(displayableList, new Ui());
        assertTrue(exitCommand.isExit);
    }
}
