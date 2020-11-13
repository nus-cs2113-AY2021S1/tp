package seedu.exceptions;

import seedu.messages.Messages;

public class UnknownCommandException extends Exception {
    @Override
    public String toString() {
        return Messages.UNKNOWN_COMMAND;
    }
}
