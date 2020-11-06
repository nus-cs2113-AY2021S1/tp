package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smarthomebot.commons.Messages.MESSAGE_EMPTY_PARAMETER;

class InvalidCommandTest {

    @Test
    public void execute_invalid_success() {
        InvalidCommand invalidCommand = new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        CommandResult expectedCommandResult = invalidCommand.execute();
        assertEquals(expectedCommandResult.feedbackToUser, MESSAGE_EMPTY_PARAMETER);
    }
}
