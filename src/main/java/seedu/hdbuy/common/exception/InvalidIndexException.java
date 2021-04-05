package seedu.hdbuy.common.exception;

public class InvalidIndexException extends Exception {

    private final String index;

    public InvalidIndexException(String index) {
        super("This index is invalid. It is due to using non-integer or out of range values.");
        this.index = index;
    }

    public String getIndex() {
        return index;
    }
}
