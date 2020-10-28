package seedu.duke.exception;

public class InsufficientQtyException extends Exception {
    public InsufficientQtyException(int quantityOwned) {
        super("You only own " + quantityOwned + " of this stock!");
    }
}
