package exception;

/**
 * Represents class when user does not input anything for locate command.
 */
public class EmptyLocationException extends InvalidCommandException {
    /**
     * Prepare message to be printed when the exception occur.
     * @return
     */
    @Override
    public String getMessage() {
        return "Please input a valid location.";
    }
}
