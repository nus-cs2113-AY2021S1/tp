package seedu.duke.exception;

public class InsufficientFundException extends Exception {
    public InsufficientFundException() {
        super("You have insufficient fund in your wallet. Please try again.");
    }
}
