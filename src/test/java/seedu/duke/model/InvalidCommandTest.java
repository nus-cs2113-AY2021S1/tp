package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.command.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidCommandTest {

    @Test
    public void getSearchKey_noInputRequired_symbolString() {
        InvalidCommand invalidCommand = new InvalidCommand("Invalid command!");
        assertEquals(invalidCommand.getErrorMessage(), "Invalid command!");
    }
}
