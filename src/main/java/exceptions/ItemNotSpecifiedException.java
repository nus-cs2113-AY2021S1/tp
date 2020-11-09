//@@author TomLBZ

package exceptions;

import constants.Constants;

public class ItemNotSpecifiedException extends CommandException {
    @Override
    public String getMessage() {
        return Constants.ITEM_NOT_SPEC;
    }
}
