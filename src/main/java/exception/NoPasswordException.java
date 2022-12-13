package exception;

/**
 * Represents the case when the user enter "/p" but did not enter password.
 */
public class NoPasswordException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Seems you forget to enter the password. If there is no password needed, do not enter \"/p\"";
    }
}
