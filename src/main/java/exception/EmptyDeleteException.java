package exception;

/**
 * Represents the case when the user types delete but do not specify
 * the task that should be deleted.
 */
public class EmptyDeleteException extends DeleteException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "☹ OOPS!!! You should enter the index of the task you want to delete.";
    }
}
