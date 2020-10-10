package seedu.financeit.common.exceptions;

public class InsufficientParamsException extends Exception {
    public InsufficientParamsException(String[] params) {
        super("Insufficient params supplied. Missing: " + String.join(", ", params));
    }
}
