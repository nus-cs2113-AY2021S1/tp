package exception;

/**
 * Represent the case when the user enters an end time for an assignment.
 */
public class DoubleTimeAssignmentException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Assignment should have only one time. If you want to indicate an exam, you may consider it as a class.";
    }
}
