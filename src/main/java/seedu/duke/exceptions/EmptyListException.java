package seedu.duke.exceptions;

//@@author killingbear999
public class EmptyListException extends DukeException {
    @Override
    public String getMessage() {
        return "Sorry, the spending list is empty.";
    }
}
