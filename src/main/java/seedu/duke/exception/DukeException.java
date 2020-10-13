package seedu.duke.exception;

public class DukeException extends Exception {
    public String message;

    public DukeException(String message) {
        this.message = message;
    }

    public void printErrorMessage() {
        System.out.println(message);
    }
}
