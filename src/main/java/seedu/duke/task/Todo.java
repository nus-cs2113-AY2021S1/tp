package seedu.duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    protected String taskType;

    private static final String TODO_FILE_SYMBOL = "T";
    private static final String SEPARATOR = "|";

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

    public String printIntoFile() {
        return TODO_FILE_SYMBOL + SEPARATOR + isDone + SEPARATOR + description;
    }
}
