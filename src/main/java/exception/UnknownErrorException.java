package exception;

/**
 * Represents the exception when it should not happen.
 */
public class UnknownErrorException extends NuScheduleException {
    @Override
    public String getMessage() {
        return "There shouldn't be such error.";
    }
}
