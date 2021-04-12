package seedu.hdbuy.common.exception;

public class NoSearchException extends Exception {

    public NoSearchException() {
        super("Please perform unit search first.");
    }
}
