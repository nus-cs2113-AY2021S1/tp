package seedu.duke.exceptions;

//@@author killingbear999
public class InvalidDateException extends DukeException {
    @Override
    public String getMessage() {
        return "Sorry, the date you input is invalid. Please try again.";
    }
}
