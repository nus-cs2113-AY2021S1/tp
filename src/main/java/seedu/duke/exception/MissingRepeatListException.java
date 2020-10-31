package seedu.duke.exception;

public class MissingRepeatListException extends DukeException {

    public MissingRepeatListException() {
        super("This event has not been repeated currently");
    }

}
