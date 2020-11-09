package exceptions;

import constants.Constants;

/**
 * The type Invalid command exception.
 */
public class ModuleNotFoundException extends ModuleException {
    @Override
    public String getMessage() {
        return Constants.MOD_NOT_FOUND;
    }
}
