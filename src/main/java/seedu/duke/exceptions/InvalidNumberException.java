package seedu.duke.exceptions;

//@@author killingbear999
public class InvalidNumberException extends DukeException {
    @Override
    public String getMessage() {
        return "Sorry, the index you input is invalid. Please try again.";
    }
}
