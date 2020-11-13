package exception;

/**
 * Represent the user call repeat but provide an invalid index for the event.
 */
public class RepeatIndexFormatException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "If you want to selected an event to repeat, enter the index of it. If you want to repeat all class "
                + "in the current week, enter \"all\".";
    }
}
