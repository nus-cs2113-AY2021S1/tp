package exception;

/**
 * Represents the exceptions when performing delete to a task not labeled with an int.
 */
public class DeleteNumberFormatException extends DeleteException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the error message
     */
    public String getMessage() {
        return "Please enter an integer after delete.";
    }
}
