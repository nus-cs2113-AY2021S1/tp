package exception;

/**
 * Represents the exception when the user types something meaningless.
 */
public class WrongCommandException extends InvalidCommandException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
