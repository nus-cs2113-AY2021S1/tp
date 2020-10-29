package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.command.SellCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellCommandTest {

    @Test
    public void getSymbol_noInputRequired_symbolString() {
        SellCommand buyCommand = new SellCommand("aapl", 10);
        assertEquals(buyCommand.getSymbol(), "aapl");
    }

    @Test
    public void getQuantity_noInputRequired_int() {
        SellCommand buyCommand = new SellCommand("aapl", 10);
        assertEquals(buyCommand.getQuantity(), 10);
    }
}
