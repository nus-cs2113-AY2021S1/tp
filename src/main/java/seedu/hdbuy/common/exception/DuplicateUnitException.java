package seedu.hdbuy.common.exception;

public class DuplicateUnitException extends Exception {

    public DuplicateUnitException() {
        super("This unit already exists.");
    }
}
