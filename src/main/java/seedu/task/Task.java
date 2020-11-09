package seedu.task;

/**
 * Provides blueprint for a Task object.
 */
public abstract class Task {
    protected String description;
    protected String location;
    protected String timeStart;
    protected String timeEnd;

    /**
     * Initialises a new Task object, and marks it as undone by default.
     *
     * @param description the description of a particular task.
     */
    public Task(String description, String location, String timeStart, String timeEnd) {
        this.description = description;
        this.location = location;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Returns a task's description.
     *
     * @return the description of the task in string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task description and status based on given format.
     *
     * @return the description and task status based on a given format.
     */
    @Override
    public String toString() {
        return this.description + this.location + this.timeStart + this.timeEnd;
    }

    public String getTimeStart() {
        return this.timeStart;
    }

    public String getTimeEnd() {
        return this.timeEnd;
    }
    
    public String getLocation() {
        return this.location;
    }

}
