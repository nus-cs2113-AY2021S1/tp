package seedu.financeit.common.exceptions;

import java.util.ArrayList;

public class InsufficientParamsException extends Exception {
    public InsufficientParamsException(ArrayList<String> params) {
        super("The following params require valid input: " + String.join(", ", params));
    }

    public InsufficientParamsException(String message) {
        super(message);
    }
}
