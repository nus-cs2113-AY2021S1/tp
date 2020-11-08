package seedu.exceptions;

import seedu.messages.Messages;

public class UnknownFileException extends Exception {
    @Override
    public String toString() {
        return Messages.NO_SUCH_FILE;
    }
}
