package exception;

public class EmptyEventIndexException extends NuScheduleException {
    public String getMessage() {
        return "Please provide a valid index.";
    }
}
