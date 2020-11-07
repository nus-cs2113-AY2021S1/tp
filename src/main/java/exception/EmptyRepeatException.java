package exception;

/**
 * Represents when the user call repeat with about argument provided.
 */
public class EmptyRepeatException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Please enter the arguments for repeat in the following format.\n "
                + "\"repeat all NUMBER_OF_WEEKS\" OR \"repeat EVENT_INDEX NUMBER_OF_WEEKS\"";
    }
}
