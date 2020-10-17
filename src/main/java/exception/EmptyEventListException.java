package exception;

/**
 * Represents the exception when the event list is empty but the user want to print or clear the list.
 */
public class EmptyEventListException extends NuScheduleException{
    @Override
    public String getMessage() {
        return "Sorry, the list is empty.";
    }
}
