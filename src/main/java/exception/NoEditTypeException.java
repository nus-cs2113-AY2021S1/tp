package exception;

public class NoEditTypeException extends NuScheduleException {
    @Override
    public String getMessage() {
        return "Please provide the details for the new event to be added.";
    }
}
