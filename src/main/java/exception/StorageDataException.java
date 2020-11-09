package exception;

/**
 * Represents an error encountered by storage data.
 */
public class StorageDataException extends Exception {
    /**
     * Error message to be thrown.
     *
     * @param message should contain relevant information of the error encountered
     */
    public StorageDataException(String message) {
        super(message);
    }
}
