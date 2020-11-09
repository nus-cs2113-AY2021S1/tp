package exceptions;

import constants.Constants;

public class DuplicateTaskException extends Exception {
    @Override
    public String getMessage() {
        return Constants.DUPLICATED_TASK;
    }
}
