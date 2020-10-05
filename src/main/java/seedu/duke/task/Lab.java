package seedu.duke.task;

/**
 * Represents a lab event.
 */
public class Lab extends Task {
    protected String date;
    protected String time;
    protected String taskType;

    public Lab(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
        this.taskType = "E";
    }

    /**
     * Return a string to describe the lab task.
     */
    @Override
    public String toString() {
        return "[LAB]" + super.toString() + " (" + date + " " + time + ")";
    }

    /** Returns the respective task type. */
    @Override
    public String getTaskType() {
        return taskType;
    }
}
