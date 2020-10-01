package seedu.duke.Task;

/**
 * Represents a Task in the task list.
 */
public abstract class Task {
    public static final String TICK_SYMBOL = "\u2713";
    public static final String CROSS_SYMBOL = "\u2718";
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
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

}
