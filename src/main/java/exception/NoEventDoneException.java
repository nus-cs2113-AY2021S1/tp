package exception;

/**
 * Represents the exception when the users try to find the study time but he had not done any thing related to academics
 * on that day.
 */
public class NoEventDoneException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Seems you haven't done any thing related to academics on that day.";
    }
}
