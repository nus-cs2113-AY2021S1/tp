package exception;

/**
 * Represents the exception happens when the user trying to find a Task with a certain keyword, but such
 * task does not exist in the list.
 */
public class NoMatchingTaskException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "Sorry, there isn't such task in the list with the given keyword.";
    }
}
