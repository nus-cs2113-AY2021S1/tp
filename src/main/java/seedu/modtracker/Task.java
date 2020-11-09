package seedu.modtracker;

/**
 * Creates a Task object.
 */
public class Task {
    protected String description;
    protected String modCode;
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param modCode     module code
     * @param description name of task
     */
    public Task(String modCode, String description) {
        this.modCode = modCode;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Gets the done status of the task.
     *
     * @return isDone
     */
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Returns a cross if task is not done.
     * Returns a tick if task is done.
     *
     * @return tick or cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "/" : "X");
    }

    public String getModCode() {
        return modCode;
    }

    /**
     * Formats the display of tasks according to tasks type.
     *
     * @return the required display format depending on type of task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + "[" + modCode + "] " + description.trim();
    }
}
