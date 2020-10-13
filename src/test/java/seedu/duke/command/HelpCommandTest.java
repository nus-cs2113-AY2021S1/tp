package seedu.duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HelpCommandTest {

    @Test
    void execute_noInput_noErrorThrown() {
        HelpCommand helpCommand = new HelpCommand();

        assertDoesNotThrow(helpCommand::execute);
    }
}
