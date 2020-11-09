package com.scrumptious.exception;

import com.scrumptious.ui.Ui;

public class ScrumptiousException extends Exception {
    public ScrumptiousException(String message) {
        super(message);
    }

    /**
     * Prints out the exception message.
     */
    public void printExceptionMessage() {
        Ui.showToUserLn(this.getMessage());
    }
}
