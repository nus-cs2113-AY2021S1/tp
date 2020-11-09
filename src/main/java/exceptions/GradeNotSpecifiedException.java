package exceptions;

import constants.Constants;

public class GradeNotSpecifiedException extends CommandException {
    @Override
    public String getMessage() {
        return Constants.GRADE_NOT_SPEC;
    }
}
