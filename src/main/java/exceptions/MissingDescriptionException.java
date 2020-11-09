package exceptions;

import constants.Constants;

public class MissingDescriptionException extends CommandException {
    @Override
    public String getMessage() {
        return Constants.NO_DESCRIPTION;
    }
}
