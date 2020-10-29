package seedu.duke.command;

public class RemoveBookmarkCommand extends Command {

    private String symbol;

    public RemoveBookmarkCommand(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
