package seedu.duke.exception;

public class NegativeQtyException extends Exception {
    public NegativeQtyException() {
        super("You cannot buy/sell zero or negative quantity!");
    }
}
