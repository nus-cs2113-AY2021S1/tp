package seedu.revised.exception.taskexception;

public class TaskDeadlineException extends Exception {
    public TaskDeadlineException(String taskDeadlineError) {
        super(taskDeadlineError);
    }
}

