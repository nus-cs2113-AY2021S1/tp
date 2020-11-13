package exception;

/**
 * Represents the case that the user want to mark an Event has an end time that is after the current time as done.
 */
public class DoneBeforeEndException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "The selected event ends at a later time. Perhaps you want to edit the end time of it, if you have "
                + "really done it.";
    }
}
