package exception;

/**
 * Represents the user call repeat but the number of weeks is entered in a wrong format.
 */
public class InvalidNumWeekException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Please enter the number of weeks to repeat. This should be an integer between 1 and 50.";
    }
}
