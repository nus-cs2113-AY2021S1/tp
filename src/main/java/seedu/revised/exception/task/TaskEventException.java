package seedu.revised.exception.task;

public class TaskEventException extends Exception {
    public TaskEventException(String taskEventError) {
        super(taskEventError);
    }
}

