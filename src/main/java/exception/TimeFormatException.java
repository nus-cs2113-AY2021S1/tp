package exception;

/**
 * Represents the exception when the date/time are entered incorrectly.
 */
public class TimeFormatException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced,
     * gives suggestion to the user on how to solve the error.
     *
     * @return the message.
     */
    public String getMessage() {
        return "You entered the time in a format that is not accepted by this program.\n"
                + "The format should be \"yyyy-MM-dd HH:mm\", and both date and time should be valid.\n"
                + "For example, 2000-01-01 00:00";
    }
}
