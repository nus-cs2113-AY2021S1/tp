package seedu.revised.exception;

public class FailedParseException extends Exception {
    public FailedParseException(String failedParseError) {
        super(failedParseError);
    }
}
