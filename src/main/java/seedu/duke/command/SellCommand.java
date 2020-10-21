package seedu.duke.command;

public class SellCommand extends Command {
    private String symbol;
    private int quantity;

    public SellCommand(String symbol, int quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }
}
