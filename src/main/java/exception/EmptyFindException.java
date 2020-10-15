package exception;

/**
 * Represents the case when the user types find but do not specify
 * the keyword.
 */
public class EmptyFindException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "☹ OOPS!!! You should enter the keyword of the tasks you want to find.";
    }
}
