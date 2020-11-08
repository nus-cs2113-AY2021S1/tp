package seedu.financeit.common.exceptions;

import seedu.financeit.common.Common;

/**
 * Exception class that is thrown when an incompatible param is supplied.
 * Whether there is a case of incompatibility will be determined by the CommandHandlers.
 *
 * Case 1:
 * new /cat tpt -i ......
 * <<<<< Exception is thrown, "tpt" is not compatible with "-i"
 */
public class IncompatibleParamsException extends Exception {
    public IncompatibleParamsException(String... incompatibleParam) {
        super(String.format("Params to \"%s\" are not compatible!", Common.convertVarArgsToString(incompatibleParam)));
    }
}
