package exception;

/**
 * Represents the case when the the user trying to operate (delete/edit/done)
 * some events that does not exist.
 */
public class UndefinedEventException extends NuScheduleException {
    private int index;//Task label of the task

    /**
     * Create an exception according to the input of the user.
     *
     * @param index the label of the event that the user want to operate on。
     */
    public UndefinedEventException(int index) {
        this.index = index;
    }

    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the error message.
     */
    public String getMessage() {
        return "OOPS!!! There isn't a event labeled " + index;
    }
}
