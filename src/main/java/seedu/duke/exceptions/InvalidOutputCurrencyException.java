package seedu.duke.exceptions;

//@@author killingbear999
public class InvalidOutputCurrencyException extends DukeException {
    @Override
    public String getMessage() {
        return "Sorry, the output currency you entered is invalid. Please try again.";
    }
}
