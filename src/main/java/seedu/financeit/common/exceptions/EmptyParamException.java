package seedu.financeit.common.exceptions;

public class EmptyParamException extends Exception {
    public EmptyParamException(String paramType) {
        super("No params supplied to " + paramType);
    }
}
