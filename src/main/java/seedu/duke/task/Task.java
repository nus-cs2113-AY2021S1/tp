package seedu.duke.task;

/**
 * Represents a Task in the task list.
 */
public abstract class Task {
    public static final String TICK_SYMBOL = "/";
    public static final String CROSS_SYMBOL = "X";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task and the description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a tick if the task is done. Returns a cross if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns empty string, will be overridden in different task types by
     * the corresponding task type.
     *
     * @return default task type is empty.
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the state of the task.
     *
     * @return the state of the task
     */
    public boolean getIsDone() {
        return isDone;
    }

}
