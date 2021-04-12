package seedu.hdbuy.common.exception;

public class EmptyParameterException extends Exception {
    public EmptyParameterException() {
        super("`find` has no parameters currently.");
    }
}
