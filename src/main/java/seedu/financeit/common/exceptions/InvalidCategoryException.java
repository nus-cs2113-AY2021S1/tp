package seedu.financeit.common.exceptions;

/**
 * Exception class is thrown when the user attempts to supply a category that is
 * out of bounds.
 * Case 1: "new /cat helloworld "
 * <<<<< Exception thrown, category is not recognised.
 */
public class InvalidCategoryException extends Exception {
    public InvalidCategoryException(String invalidParam) {
        super(String.format("\"%s\" is not a valid category input!", invalidParam));
    }
}
