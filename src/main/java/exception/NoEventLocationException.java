package exception;

/**
 * Represents the exception when no location is provided when adding a new event.
 */
public class NoEventLocationException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "Please input a valid location and mark it with \"/l\" for underline locations or \"/o\" for online "
                + "locations.";
    }
}
