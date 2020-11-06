package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandResultTest {

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // test if same value -> returns true
        assertTrue(commandResult.feedbackToUser.equals("feedback"));

        // test if not same value -> return false
        assertFalse(commandResult.feedbackToUser.equals("False"));

    }
}
