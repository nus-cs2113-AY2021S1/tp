package seedu.financeit.common.exceptions;

/**
 * Exception class that is thrown when the user attempts to create an item when there exists an identical
 * item previously added into the itemList.
 * Case 1:
 * Ledger 200404 added
 * Ledger 200404 added
 * <<<<< Exception thrown here
 */
public class DuplicateInputException extends Exception {
    public DuplicateInputException() {
        super();
    }
}
