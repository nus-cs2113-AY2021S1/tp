package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddBookmarkCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBookmarkCommandTest {

    @Test
    public void getSymbol_noInputRequired_symbolString() {
        AddBookmarkCommand addBookmarkCommand = new AddBookmarkCommand("aapl");
        assertEquals(addBookmarkCommand.getSymbol(), "aapl");
    }
}
