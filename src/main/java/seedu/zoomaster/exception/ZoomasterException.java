package seedu.zoomaster.exception;

/**
 * The class DukeException is a subclass of Exception.
 * It is thrown when an error specific to Duke occurs.
 */
public class ZoomasterException extends Exception {
    private ZoomasterExceptionType zoomasterExceptionType;
    private String info;

    /**
     * Constructs a new DukeException instance by storing the given DukeExceptionType.
     *
     * @param dukeExceptionType The type of dukeException.
     */
    public ZoomasterException(ZoomasterExceptionType dukeExceptionType) {
        this.zoomasterExceptionType = dukeExceptionType;
    }

    /**
     * Constructs a new DukeException instance by storing the given DukeExceptionType and message.
     *
     * @param dukeExceptionType The type of dukeException.
     * @param info Additional information of the exception.
     */
    public ZoomasterException(ZoomasterExceptionType dukeExceptionType, String info) {
        this.zoomasterExceptionType = dukeExceptionType;
        this.info = info;
    }

    /**
     * Returns the dukeExceptionType of the dukeException.
     *
     * @return the dukeExceptionType.
     */
    public ZoomasterExceptionType getError() {
        return zoomasterExceptionType;
    }

    public String getInfo() {
        return info;
    }


}
