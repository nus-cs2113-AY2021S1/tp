package seedu.duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    protected String taskType;

    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Returns a [T] icon to indicate task as a todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /** Returns the respective task type. */
    @Override
    public String getTaskType() {
        return taskType;
    }
}
