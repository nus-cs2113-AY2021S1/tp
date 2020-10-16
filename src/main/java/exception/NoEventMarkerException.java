package exception;

/**
 * Represents the exception where there is no timer marker in the command
 */
public class NoEventMarkerException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "Please enter your command in the correct format.";
    }
}
