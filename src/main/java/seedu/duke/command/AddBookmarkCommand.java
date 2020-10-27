package seedu.duke.command;

public class AddBookmarkCommand extends Command {

    private String symbol;

    public AddBookmarkCommand(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
