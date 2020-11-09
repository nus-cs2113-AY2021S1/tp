package exceptions;

import constants.Constants;

public class TooManyArgumentsException extends CommandException {
    @Override
    public String getMessage() {
        return Constants.TOO_MANY_ARGS;
    }
}
