package seedu.duke.exception;

public class DoNotOwnStockException extends Exception {
    public DoNotOwnStockException() {
        super("You do not own this stock!");
    }
}
