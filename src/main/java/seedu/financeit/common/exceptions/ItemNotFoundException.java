package seedu.financeit.common.exceptions;

/**
 * Exception class is thrown when the user attempts to access an item
 * which does not exist in an itemList.
 * Case 1: "delete /id -5"
 * <<<<< Exception thrown.
 */
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super("The object cannot be found!");
    }
}
