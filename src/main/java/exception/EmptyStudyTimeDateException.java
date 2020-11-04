package exception;

/**
 * Represents the case when the user types studyTime but do not specify
 * the date.
 */
public class EmptyStudyTimeDateException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "OOPS!!! You should enter the date that you want to know your study time.";
    }
}
