package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.command.BuyCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyCommandTest {

    @Test
    public void getSymbol_noInputRequired_symbolString() {
        BuyCommand buyCommand = new BuyCommand("aapl", 10);
        assertEquals(buyCommand.getSymbol(), "aapl");
    }

    @Test
    public void getQuantity_noInputRequired_int() {
        BuyCommand buyCommand = new BuyCommand("aapl", 10);
        assertEquals(buyCommand.getQuantity(), 10);
    }
}
