package exception;

/**
 * Represents the exception happens when the user trying to create an Event or a Deadline
 * but the time is not given in the correct format.
 */
public class TimeFormatException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced,
     * gives suggestion to the user on how to solve the error.
     *
     * @return the message
     */
    public String getMessage() {
        return "You entered the time in a format that is not accepted by this program.\n" +
                "The format should be \"yyyy-MM-dd HH:mm\"\n" +
                "For example, 2000-01-01 00:00";
    }
}
