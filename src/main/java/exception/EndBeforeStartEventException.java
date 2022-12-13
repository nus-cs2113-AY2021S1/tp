package exception;

/**
 * Represents the case when the user want to create an Event that ends before it starts.
 */
public class EndBeforeStartEventException extends NuScheduleException {
    @Override
    public String getMessage() {
        return "Just like you cannot die before you exist, an event cannot end before it starts.";
    }
}
