package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smarthomebot.logic.commands.ExitCommand.isExit;

class ExitCommandTest {

    @Test
    void isExit_returnTrue() {
        Command c = new ExitCommand();
        assertEquals(true, isExit(c));
    }

    @Test
    void isExit_returnFalse() {
        Command c = new ListCommand("appliance", "");
        assertEquals(false, isExit(c));
    }

}
