package seedu.financeit.common.exceptions;

import java.util.ArrayList;

public class InsufficientParamsException extends Exception {
    public InsufficientParamsException(ArrayList<String> params) {
        super("The following params require input: " + String.join(", ", params));
    }
}
