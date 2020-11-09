package seedu.exceptions;

import seedu.messages.Messages;

public class InvalidFormatException extends Exception {
    @Override
    public String toString() {
        return Messages.INVALID_FORMAT;
    }
}
