package exception;
/**
 * Represents the exception happens when the user trying to find the tasks on a certain date
 * but the date is not given in the correct format.
 */
public class DateFormatException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced,
     * gives suggestion to the user on how to solve the error.
     *
     * @return the message.
     */
    public String getMessage() {
        return "You entered a date in a format that is not accepted by this function.\n" +
                "The format should be \"yyyy-MM-dd\"\n" +
                "For example, 2000-01-01";
    }
}
