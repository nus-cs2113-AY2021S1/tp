package seedu.financeit.common.exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super("The object cannot be found!");
    }
}
