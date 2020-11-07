package seedu.duke.exception;

import seedu.duke.ui.Ui;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public void printExceptionMessage() {
        Ui.showToUserLn(this.getMessage());
    }
}
