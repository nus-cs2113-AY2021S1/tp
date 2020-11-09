package seedu.duke.exception;

public class InvalidEventDateException extends DukeException {

    public InvalidEventDateException() {
        super("This event does not occur on this date.");
    }
}
