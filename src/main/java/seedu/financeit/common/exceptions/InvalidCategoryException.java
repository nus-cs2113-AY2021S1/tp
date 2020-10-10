package seedu.financeit.common.exceptions;

public class InvalidCategoryException extends Exception {
    public InvalidCategoryException(String invalidParam) {
        super(String.format("\"%s\" is not a valid category input!", invalidParam));
    }
}
