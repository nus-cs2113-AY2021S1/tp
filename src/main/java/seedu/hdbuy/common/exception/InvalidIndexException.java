package seedu.hdbuy.common.exception;

public class InvalidIndexException extends Exception {

    public InvalidIndexException(String index) {
        super(index + " is out of range. Check that index is within the range of units displayed.");
    }
}
