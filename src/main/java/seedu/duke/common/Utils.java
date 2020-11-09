package seedu.duke.common;

//@@author GuoAi-reused
//Reused from https://github.com/GuoAi/ip with minor modifications

import seedu.duke.DukeException;

/**
 * Utility methods.
 */
public class Utils {

    /**
     * Convert strings (either "0" or "1") to booleans (false or true respectively).
     *
     * @param str input string (either "0" or "1").
     * @return false if input string is "0", true if "1".
     * @throws DukeException If boolean value is neither 1 nor 0.
     */
    public static boolean stringToBoolean(String str) throws DukeException {
        if (str.equals("0")) {
            return false;
        } else if (str.equals("1")) {
            return true;
        }

        throw new DukeException(Messages.EXCEPTION_INVALID_BOOLEAN);
    }

}
