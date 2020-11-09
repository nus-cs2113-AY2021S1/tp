package seedu.duke.exceptions;

//@@author killingbear999
public class InvalidCurrencyException extends DukeException {
    @Override
    public String getMessage() {
        return "Sorry, the system  only supports currency conversion for SGD to USD, or USD to SGD, "
                       + "or SGD to CNY, or CNY to SGD.";
    }
}
