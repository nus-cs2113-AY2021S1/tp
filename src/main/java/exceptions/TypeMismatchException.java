package exceptions;

import constants.Constants;

public class TypeMismatchException extends CommandException {
    @Override
    public String getMessage() {
        return Constants.TYPE_MISMATCH;
    }
}
