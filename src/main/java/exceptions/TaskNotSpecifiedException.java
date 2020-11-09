//@@author TomLBZ

package exceptions;

import constants.Constants;

public class TaskNotSpecifiedException extends CommandException {
    @Override
    public String getMessage() {
        return Constants.TASK_NOT_SPEC;
    }
}
