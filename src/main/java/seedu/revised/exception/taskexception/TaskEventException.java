package seedu.revised.exception.taskexception;

public class TaskEventException extends Exception {
    public TaskEventException(String taskEventError) {
        super(taskEventError);
    }
}

