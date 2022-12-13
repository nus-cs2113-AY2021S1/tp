package exception;

/**
 * Represents the exception happens when the user trying to create an event
 * without using /l to give it a location.
 */
public class NoEventLocationMarkerException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    public String getMessage() {
        return "Please mark the location of the event by \"/l\".";
    }
}
