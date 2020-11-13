package exception;

public class NoEditEventDescriptionException extends ClassException {
    @Override
    public String getMessage() {
        return "Please provide the details for the new event to be added.";
    }
}
