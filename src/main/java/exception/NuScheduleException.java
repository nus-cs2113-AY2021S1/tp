package exception;

public abstract class NuScheduleException extends Exception {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    public abstract String getMessage();
}
