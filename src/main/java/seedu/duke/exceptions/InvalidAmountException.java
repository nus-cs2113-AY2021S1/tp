package seedu.duke.exceptions;

//@@author killingbear999
public class InvalidAmountException extends DukeException {
    @Override
    public String getMessage() {
        return "Sorry, the amount input is invalid. Please try again. \nThe amount input should be larger than 0.01. "
                       + "Negative number and extremely small amount will be considered as invalid input.";
    }
}
