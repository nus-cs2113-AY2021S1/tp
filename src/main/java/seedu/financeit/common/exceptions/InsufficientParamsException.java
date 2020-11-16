package seedu.financeit.common.exceptions;

import java.util.ArrayList;

/**
 * Exception class that is thrown when a CommandHandler did not successfully parse all of the required params.
 * Case 1: Not enough valid params supplied by user.
 * Case 2: Invalid param supplied to a valid param type.
 */
public class InsufficientParamsException extends Exception {
    public InsufficientParamsException(ArrayList<String> params) {
        super("The following params require valid input: " + String.join(", ", params));
    }

    public InsufficientParamsException(String message) {
        super(message);
    }
}
