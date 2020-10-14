package exception;

/**
 * Represents the exception when no date/time is inputted as expected.
 */
public class NoEventTimeException extends NuScheduleException{
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "Please input the date/time.";
    }
}
