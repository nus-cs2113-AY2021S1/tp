package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.command.SellCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellCommandTest {

    @Test
    public void getSymbol_noInputRequired_symbolString() {
        SellCommand sellCommand = new SellCommand("aapl", 10);
        assertEquals(sellCommand.getSymbol(), "aapl");
    }

    @Test
    public void getQuantity_noInputRequired_int() {
        SellCommand sellCommand = new SellCommand("aapl", 10);
        assertEquals(sellCommand.getQuantity(), 10);
    }
}
