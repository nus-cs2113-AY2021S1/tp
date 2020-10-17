package exception;

/**
 * Represents the case when the user types date but do not specify
 * the date.
 */
public class EmptyFindDateException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "OOPS!!! You should enter the date you are looking for.";
    }
}
