package seedu.duke;

/**
 * Signals an error caused by an invalid command input by the user.
 */
public class DukeException extends Exception {

    private String exception;

    public DukeException(String message) {
        super(message);
        exception = message;
    }

    public String getException() {
        return exception;
    }
}
