package seedu.exceptions;

import seedu.messages.Messages;

public class InvalidReminderException extends Exception{

    public String toString() {
        return Messages.INVALID_REMINDER;
    }
}
