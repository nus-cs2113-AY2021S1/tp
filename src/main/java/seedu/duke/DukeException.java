package seedu.duke;

public class DukeException extends Exception {
    protected String message;

    /**
     * Constructor for exception message.
     *
     * @param message exception message.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }
}
