package seedu.financeit.common.exceptions;

/**
 * Exception class is thrown when the user supplied an invalid param to the param type.
 * Whether a param is invalid would be determined in the ParamChecker singleton class.
 *
 * Case 1: "new /amt -1500"
 * <<<<< Exception thrown, input amount cannot be negative.
 */
public class ParseFailParamException extends Exception {
    public ParseFailParamException(String param) {
        super("Failed to parse the following param: " + param);
    }
}
