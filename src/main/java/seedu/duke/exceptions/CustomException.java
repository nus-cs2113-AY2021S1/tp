package seedu.duke.exceptions;


public class CustomException extends Exception {

    private ExceptionType exception;

    public CustomException(ExceptionType exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return (exception.getMessage());
    }
}