package exception;

/**
 * Represents the case when the user want to create an empty Deadline.
 */
public class EmptyClassException extends ClassException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }
}
