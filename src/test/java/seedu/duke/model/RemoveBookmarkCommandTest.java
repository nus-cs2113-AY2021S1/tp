package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.command.RemoveBookmarkCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveBookmarkCommandTest {

    @Test
    public void getSymbol_noInputRequired_symbolString() {
        RemoveBookmarkCommand removeBookmarkCommand = new RemoveBookmarkCommand("aapl");
        assertEquals(removeBookmarkCommand.getSymbol(), "aapl");
    }
}
