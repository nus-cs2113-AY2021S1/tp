package seedu.financeit.common.exceptions;

public class ParseFailParamException extends Exception {
    public ParseFailParamException(String param) {
        super("Failed to parse the following param: " + param);
    }
}
