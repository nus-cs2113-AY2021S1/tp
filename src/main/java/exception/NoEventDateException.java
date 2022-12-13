package exception;

/**
 * Represents the exception happens when the user trying to find a Event with a certain date,
 * but such Event does not exist in the list.
 */
public class NoEventDateException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "Sorry, there isn't any event in the list on the given date.";
    }
}
