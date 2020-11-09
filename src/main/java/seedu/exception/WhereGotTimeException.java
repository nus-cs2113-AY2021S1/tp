package seedu.exception;

public class WhereGotTimeException extends Exception {

    /**
     * Returns an exception error.
     *
     * @param message the reason for exception.
     */
    public WhereGotTimeException(String message) {
        super(message);
    }
}
