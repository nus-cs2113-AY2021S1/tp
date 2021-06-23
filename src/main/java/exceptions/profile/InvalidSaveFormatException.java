package exceptions.profile;

import exceptions.SchwarzeneggerException;

import static ui.profile.ProfileUi.MESSAGE_INVALID_SAVE_FORMAT;

//@@author tienkhoa16
/**
 * Represents exception when there is corruption in data save format.
 */
public class InvalidSaveFormatException extends SchwarzeneggerException {

    /**
     * Constructs InvalidSaveFormatException object inheriting abstract class SchwarzeneggerException.
     *
     * @param filePath Path to file with invalid save format.
     */
    public InvalidSaveFormatException(String filePath) {
        super(String.format(MESSAGE_INVALID_SAVE_FORMAT, filePath));
    }

}
