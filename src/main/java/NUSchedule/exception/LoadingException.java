package NUSchedule.exception;

/**
 * Represents the exception when the user edit the file in a wrong way.
 */
public class LoadingException extends NUScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "You edit the file in a wrong format. Please check.";
    }
}
