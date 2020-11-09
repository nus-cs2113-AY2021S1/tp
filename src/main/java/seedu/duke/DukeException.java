package seedu.duke;

//@@author GuoAi-reused
//Reused from https://github.com/GuoAi/ip with minor modifications

/**
 * Signals an error caused by Duke commands and operations.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

