package exceptions.profile;

import exceptions.SchwarzeneggerException;

import static ui.profile.ProfileUi.MESSAGE_INVALID_HEIGHT;

//@@author tienkhoa16
/**
 * Represents exception when input height is invalid.
 */
public class InvalidHeightException extends SchwarzeneggerException {

    /**
     * Constructs InvalidHeightException object inheriting abstract class SchwarzeneggerException.
     */
    public InvalidHeightException() {
        super(MESSAGE_INVALID_HEIGHT);
    }
}
