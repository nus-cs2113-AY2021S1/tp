//@@author TomLBZ

package exceptions;

import constants.Constants;

public class InvalidListException extends CommandException {
    @Override
    public String getMessage() {
        return Constants.INVALID_LIST;
    }
}
