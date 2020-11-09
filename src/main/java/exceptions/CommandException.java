//@@author TomLBZ

package exceptions;

import constants.Constants;

/**
 * The type Command exception.
 */
public class CommandException extends Exception {
    @Override
    public String getMessage() {
        return Constants.INVALID;
    }
}
