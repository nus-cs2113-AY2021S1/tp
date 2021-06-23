package exceptions;

/**
 * Represents exception when date time format is invalid.
 */
public class InvalidDateFormatException extends SchwarzeneggerException {

    /**
     * Constructs InvalidDateFormatException object inheriting abstract class SchwarzeneggerException.
     */
    public InvalidDateFormatException() {
        super("Wrong format, please enter in the format: \n"
                + "\t new /d [DATE: DD-MM-YYYY]");
    }
}
