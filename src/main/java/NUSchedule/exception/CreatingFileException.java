package NUSchedule.exception;

/**
 * Represents the exception happen when the file is not created successfully.
 */
public class CreatingFileException extends NUScheduleException {
    private final String filePath;

    public CreatingFileException(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the error message
     */
    public String getMessage() {
        return "The file \"" + filePath + "\" is not created";
    }
}
