package seedu.duke.exceptions;

//@@author killingbear999
public class InvalidBudgetException extends DukeException {
    @Override
    public String getMessage() {
        return "Sorry, the budget amount you entered is invalid. Please try again.";
    }
}
