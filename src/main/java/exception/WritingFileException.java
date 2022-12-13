package exception;

/**
 * Represents the exception when the file is not correctly written.
 */
public class WritingFileException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "Something went wrong when writing to the file.";
    }
}
