package exception;

public class InvalidEditTypeException extends InvalidCommandException {
    @Override
    public String getMessage() {
        return "Event type can only be personalevent, class or assignment.";
    }
}
