package seedu.financeit.common.exceptions;

import seedu.financeit.common.Common;

public class IncompatibleParamsException extends Exception {
    public IncompatibleParamsException(String... incompatibleParam) {
        super(String.format("Params to \"%s\" are not compatible!", Common.convertVarArgsToString(incompatibleParam)));
    }
}
