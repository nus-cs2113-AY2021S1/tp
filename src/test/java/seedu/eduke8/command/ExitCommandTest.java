package seedu.eduke8.command;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.DisplayableList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest extends Eduke8Test {

    @Test
    void execute_normal_isExitIsTrue() {
        Command exitCommand = new ExitCommand();
        DisplayableList displayableList = createTestOptionList();
        exitCommand.execute(displayableList, ui);
        assertTrue(exitCommand.isExit);
    }
}
