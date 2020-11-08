package seedu.financeit.common.exceptions;

/**
 * Exception class is thrown when the CommandHandler requires a param to be supplied to a corresponding
 * param type, but the user did not supply any.
 *
 * Case 1: "commandString /param "
 */
public class EmptyParamException extends Exception {
    public EmptyParamException(String paramType) {
        super("No params supplied to " + paramType);
    }
}
