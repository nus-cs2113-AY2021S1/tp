package exception;

/**
 * Represents the exception when no description is provided for the event.
 */
public class EmptyEventException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "Your input is incomplete. Please enter an event description.";
    }
}
