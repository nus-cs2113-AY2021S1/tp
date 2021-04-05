package seedu.hdbuy.common.exception;

public class MissingFileException extends Exception {

    public MissingFileException(String path) {
        super("Data file is missing at: " + path);
    }
}
