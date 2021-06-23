package exceptions.profile;

import exceptions.SchwarzeneggerException;

import static ui.profile.ProfileUi.MESSAGE_INVALID_COMMAND_FORMAT;

//@@author tienkhoa16
/**
 * Represents exception when command format is invalid.
 */
public class InvalidCommandFormatException extends SchwarzeneggerException {

    /**
     * Constructs InvalidCommandFormatException object inheriting abstract class SchwarzeneggerException.
     *
     * @param format Correct format of the command.
     */
    public InvalidCommandFormatException(String format) {
        super(String.format(MESSAGE_INVALID_COMMAND_FORMAT, format));
    }
}
