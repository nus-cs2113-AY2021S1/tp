package seedu.trippie.exception;

public class TrippieException extends Exception {
    public String message;

    public TrippieException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
