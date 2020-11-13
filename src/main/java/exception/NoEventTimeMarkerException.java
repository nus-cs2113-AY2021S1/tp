package exception;

/**
 * Represents the exception where there is no time marker in the command.
 */
public class NoEventTimeMarkerException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    public String getMessage() {
        return "Please mark the time of the event by \"/t\".";
    }
}
