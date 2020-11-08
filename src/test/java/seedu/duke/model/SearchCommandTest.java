package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.command.SearchCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchCommandTest {

    @Test
    public void getSearchKey_noInputRequired_symbolString() {
        SearchCommand searchCommand = new SearchCommand("aapl");
        assertEquals(searchCommand.getSearchKey(), "aapl");
    }
}
