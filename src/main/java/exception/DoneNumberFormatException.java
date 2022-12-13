package exception;

/**
 * Represents the exceptions when performing done to a task not labeled with an int.
 */
public class DoneNumberFormatException extends DoneException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the error message
     */
    public String getMessage() {
        return "Please enter an integer after done.";
    }
}
