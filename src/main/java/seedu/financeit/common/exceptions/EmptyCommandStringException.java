package seedu.financeit.common.exceptions;

/**
 * Exception class that is thrown when the user did not supply a command string.
 *
 * Case 1: "/param1 param"
 */
public class EmptyCommandStringException extends Exception {
    public EmptyCommandStringException() {
        super("Command string cannot be empty!");
    }
}
