package exceptions.profile;

import exceptions.SchwarzeneggerException;

import static ui.profile.ProfileUi.MESSAGE_SAVING_ERROR;

//@@author tienkhoa16
/**
 * Represents exception while saving data.
 */
public class SavingException extends SchwarzeneggerException {

    /**
     * Constructs SavingException object inheriting abstract class SchwarzeneggerException.
     *
     * @param message The cause of the error.
     */
    public SavingException(String message) {
        super(String.format(MESSAGE_SAVING_ERROR, message));
    }
}
