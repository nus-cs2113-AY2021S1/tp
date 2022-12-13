package exception;

/**
 * Represents the case when the user types done but do not specify
 * the task that should be done.
 */
public class EmptyDoneException extends DoneException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "OOPS!!! You should enter the index of the task you have done.";
    }
}
