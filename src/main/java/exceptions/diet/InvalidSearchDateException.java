package exceptions.diet;

import exceptions.SchwarzeneggerException;

/**
 * Represents exception when search date is invalid.
 */
public class InvalidSearchDateException extends SchwarzeneggerException {

    /**
     * Constructs InvalidSearchDateException object inheriting class SchwarzeneggerException.
     */
    public InvalidSearchDateException() {
        super("Starting date should be earlier than end date.");
    }
}
