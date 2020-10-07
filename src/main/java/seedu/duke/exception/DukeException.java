package seedu.duke.exception;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public void printExceptionMessage() {
        System.out.println("Exception found: " + this.getMessage());
    }
}
