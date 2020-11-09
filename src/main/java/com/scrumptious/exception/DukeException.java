package com.scrumptious.exception;

import com.scrumptious.ui.Ui;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Prints out the exception message.
     */
    public void printExceptionMessage() {
        Ui.showToUserLn(this.getMessage());
    }
}
