package exception;

/**
 * Represents the exception when an invalid command is inputted.
 */
public class WrongCommandException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "Please enter a valid command.";
    }
}
