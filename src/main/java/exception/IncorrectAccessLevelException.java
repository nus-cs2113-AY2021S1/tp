package exception;

/**
 * Represents an error when a command is input at the incorrect access level.
 */
public class IncorrectAccessLevelException extends Exception {
    /**
     * Error message to be thrown.
     *
     * @param message should contain relevant information on the access level which the command can be input
     */
    public IncorrectAccessLevelException(String message) {
        super(message);
    }
}
