package exception;

/**
 * Represents the exception happen when the data file is found.
 */
public class DataFileNotFoundException extends NuScheduleException {
    private String fileName;

    public DataFileNotFoundException(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return fileName + "is not found. Did you forget to download the data folder provided in the release?";
    }
}
