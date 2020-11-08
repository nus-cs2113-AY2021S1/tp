package exception;

/**
 * Represent when the user call repeat without provide the number of weeks to repeat.
 */
public class NoRepeatWeekException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Please enter the number of weeks to repeat the selected event(s).";
    }
}
