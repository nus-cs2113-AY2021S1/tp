package seedu.revised.exception.storageexception;

public class DataLoadingException extends Exception {
    public DataLoadingException(String errorMessage) {
        super(errorMessage);
    }

    public DataLoadingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
