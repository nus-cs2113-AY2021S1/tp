package exception;

/**
 * Represents the exception when the index for edit is of a wrong format.
 */
public class WrongEditFormatException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Index of an even should be an integer.";
    }
}
