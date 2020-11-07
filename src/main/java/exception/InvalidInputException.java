package exception;

/**
 * Represents an error that the given input is invalid.
 */
public class InvalidInputException extends Exception {
    /**
     * Error message to be thrown.
     *
     * @param message should contain relevant information on why the input is invalid
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
