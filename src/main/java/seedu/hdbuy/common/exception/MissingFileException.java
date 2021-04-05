package seedu.hdbuy.common.exception;

public class MissingFileException extends Exception {

    public MissingFileException() {
        super("Data file is missing");
    }
}
