package NUSchedule.exception;

/**
 * Represents the exception when the file is not correctly written.
 */
public class WritingFileException extends NUScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "something went wrong when writing to file";
    }
}
