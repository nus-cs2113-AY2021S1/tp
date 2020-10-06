package seedu.duke.task;

/**
 * Represents a lab event.
 */
public class Lab extends Task {
    protected String date;
    protected String time;
    protected String taskType;

    private static final String LAB_FILE_SYMBOL = "LAB";
    private static final String SEPARATOR = "|";

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

    @Override
    public String printIntoFile() {
        return LAB_FILE_SYMBOL + SEPARATOR + isDone + SEPARATOR + description
                + SEPARATOR + this.date + SEPARATOR + this.time;
    }

    /** Returns the respective task type. */
    @Override
    public String getTaskType() {
        return taskType;
    }
}
