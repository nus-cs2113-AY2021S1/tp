package seedu.duke;

public class DietException extends Exception {
    public DietException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
