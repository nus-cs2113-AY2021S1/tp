package seedu.revised.exception.storage;

public class DataLoadingException extends Exception {
    public DataLoadingException(String errorMessage) {
        super(errorMessage);
    }

    public DataLoadingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
