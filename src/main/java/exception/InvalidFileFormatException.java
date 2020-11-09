package exception;

/**
 * Represents an error when the content in the file is invalid.
 */
public class InvalidFileFormatException extends Exception {
    /**
     * Error message to be thrown.
     *
     * @param message should contain relevant information on why the file format is invalid
     */
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
