package seedu.duke.task;

/**
 * Represents a tutorial event.
 */
public class Tutorial extends Task {
    protected String date;
    protected String time;
    protected String taskType;


    public Tutorial(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
        this.taskType = "E";
    }

    /**
     * Return a string to describe the tutorial task.
     */
    @Override
    public String toString() {
        return "[TUT]" + super.toString() + " (" + date + " " + time + ")";
    }

    /** Returns the respective task type. */
    @Override
    public String getTaskType() {
        return taskType;
    }
}
