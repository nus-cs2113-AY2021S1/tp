package exception;

/**
 * Represent the case when the user type repeat all but there is no class in the current week.
 */
public class NoClassWeekException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Seems there is no class in this week.";
    }
}
