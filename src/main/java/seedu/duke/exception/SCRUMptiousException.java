package seedu.duke.exception;

public class SCRUMptiousException extends Exception {
    public SCRUMptiousException (String message) {
        super(message);
    }

    public void printExceptionMessage() {
        System.out.println("Exception found: " + this.getMessage());
    }
}
