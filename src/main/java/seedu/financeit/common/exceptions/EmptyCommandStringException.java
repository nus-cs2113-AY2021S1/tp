package seedu.financeit.common.exceptions;

public class EmptyCommandStringException extends Exception {
    public EmptyCommandStringException() {
        super("Command string cannot be empty!");
    }
}
