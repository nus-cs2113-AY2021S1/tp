package exception;

/**
 * Represents the exception happens when the user trying to create a Deadline
 * without giving it a time.
 */
public class NoClassTimeException extends ClassException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "OOPS!!! You should enter a time for deadline.";
    }
}
