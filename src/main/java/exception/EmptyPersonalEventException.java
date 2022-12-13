package exception;

/**
 * Represents the case when the user want to create an empty Event.
 */
public class EmptyPersonalEventException extends PersonalEventException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "OOPS!!! The description of an event cannot be empty.";
    }
}
