package seedu.duke.exception;

public class MissingDeadlineRepeatException extends DukeException {


    public MissingDeadlineRepeatException() {
        super("Error! You cannot repeat an event that has no deadline!");

    }
}
