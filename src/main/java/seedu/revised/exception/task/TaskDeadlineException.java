package seedu.revised.exception.task;

public class TaskDeadlineException extends Exception {
    public TaskDeadlineException(String taskDeadlineError) {
        super(taskDeadlineError);
    }
}

