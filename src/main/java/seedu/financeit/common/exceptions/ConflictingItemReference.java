package seedu.financeit.common.exceptions;

import seedu.financeit.common.Constants;
import seedu.financeit.common.Item;
import seedu.financeit.ui.UiManager;
import java.util.Collection;


public class ConflictingItemReference extends Exception {
    public ConflictingItemReference(Collection items) {
        String message = "";
        for (Object item : items) {
            message += ((Item)item).getName() + ", ";
        }
        UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
            "Multiple conflicting item references. Stick to one reference!",
            message);
    }
}
