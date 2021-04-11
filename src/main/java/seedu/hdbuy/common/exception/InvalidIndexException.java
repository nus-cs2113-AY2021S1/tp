package seedu.hdbuy.common.exception;

public class InvalidIndexException extends Exception {

    public InvalidIndexException(String index) {
        super(index + " is invalid. It is either a non-integer or an out of range value.");
    }
}
