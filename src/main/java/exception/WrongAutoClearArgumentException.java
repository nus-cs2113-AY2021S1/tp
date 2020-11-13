package exception;

/**
 * Represents the case when the user types autoClear but did not provide an arguement.
 */
public class WrongAutoClearArgumentException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "You should type either \"autoClear on\" or \"autoClear off\" to use this function.";
    }
}
