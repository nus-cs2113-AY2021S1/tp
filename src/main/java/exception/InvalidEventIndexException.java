package exception;

/**
 * Represents exception when event index is out of list.
 */
public class InvalidEventIndexException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return "Please input an event number that is within the event list.";
    }
}
