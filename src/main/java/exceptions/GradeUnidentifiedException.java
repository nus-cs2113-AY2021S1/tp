package exceptions;

import constants.Constants;

public class GradeUnidentifiedException extends CommandException {
    @Override
    public String getMessage() {
        return Constants.GRADE_NOT_FOUND;
    }
}
