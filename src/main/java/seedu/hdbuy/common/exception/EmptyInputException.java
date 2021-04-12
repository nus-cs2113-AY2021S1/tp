package seedu.hdbuy.common.exception;

public class EmptyInputException extends Exception {

    public EmptyInputException() {
        super("Did you forget to type something?");
    }
}
