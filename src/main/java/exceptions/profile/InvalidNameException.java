package exceptions.profile;

import exceptions.SchwarzeneggerException;

import static ui.profile.ProfileUi.MESSAGE_INVALID_NAME;

//@@author tienkhoa16
/**
 * Represents exception when input name is invalid.
 */
public class InvalidNameException extends SchwarzeneggerException {

    /**
     * Constructs InvalidNameException object inheriting abstract class SchwarzeneggerException.
     */
    public InvalidNameException() {
        super(MESSAGE_INVALID_NAME);
    }
}
