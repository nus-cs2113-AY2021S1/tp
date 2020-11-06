package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    @Test
    public void execute_help_success() {
        HelpCommand helpCommand = new HelpCommand();
        CommandResult expectedCommandResult = helpCommand.execute();
        assertEquals(expectedCommandResult.feedbackToUser, HelpCommand.MESSAGE_HELP);
    }
}
