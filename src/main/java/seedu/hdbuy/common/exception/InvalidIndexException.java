package seedu.hdbuy.common.exception;

public class InvalidIndexException extends Exception {

    public InvalidIndexException(String index) {
        super(index + " is invalid. It is due to using non-integer or out of range values.");
    }
}
