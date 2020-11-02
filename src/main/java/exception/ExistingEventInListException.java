package exception;

/**
 * represent the user trys to add an event that is already in the list.
 */
public class ExistingEventInListException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Seems you have already added this event. Do not input duplicate events.";
    }
}
