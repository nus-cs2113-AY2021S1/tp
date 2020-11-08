package seedu.revised.exception.taskexception;

public class TaskTodoException extends Exception {
    public TaskTodoException(String taskTodoError) {
        super(taskTodoError);
    }
}

