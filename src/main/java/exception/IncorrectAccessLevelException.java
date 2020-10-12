package exception;

public class IncorrectAccessLevelException extends Exception {
    public IncorrectAccessLevelException() {
    }

    public IncorrectAccessLevelException(String message) {
        super(message);
    }
}
