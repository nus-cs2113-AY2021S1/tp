package exception;

/**
 * Represents the case when the the user trying to delete
 * some tasks that does not exist.
 */
public class DeleteUndefinedTaskException extends DeleteException {
    private int label;//Task label of the task

    public DeleteUndefinedTaskException() {
    }


    /**
     * Create an exception according to the input of the user.
     *
     * @param label the label of the task that the user want to delete
     */
    public DeleteUndefinedTaskException(int label) {
        this.label = label;
    }

    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the error message
     */
    public String getMessage() {
        return "☹ OOPS!!! There isn't a task labeled " + label;
    }
}
