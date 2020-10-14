package exception;

/**
 * Represents the exception when the date/time are entered incorrectly
 */
public class TimeFormatException extends NuScheduleException{
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "Please enter the date/time in the correct format.";
    }
}
