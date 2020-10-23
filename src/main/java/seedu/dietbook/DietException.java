package seedu.dietbook;

public class DietException extends Exception {
    public DietException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
