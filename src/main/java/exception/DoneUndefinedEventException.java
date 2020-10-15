package exception;

/**
 * Represents the case when the the user trying to done
 * some tasks that does not exist.
 */
public class DoneUndefinedEventException extends DoneException {
    private int label;//Task label of the task

    public DoneUndefinedEventException() {
    }


    /**
     * Create an exception according to the input of the user.
     *
     * @param label the label of the task that the user want to mark as done
     */
    public DoneUndefinedEventException(int label) {
        this.label = label;
    }

    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the error message
     */
    public String getMessage() {
        return "OOPS!!! There isn't a task labeled " + label;
    }
}
