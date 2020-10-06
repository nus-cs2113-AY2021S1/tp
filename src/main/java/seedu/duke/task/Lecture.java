package seedu.duke.task;

/**
 * Represents a lecture event.
 */
public class Lecture extends Task {
    protected String date;
    protected String time;
    protected String taskType;

    private static final String LECTURE_FILE_SYMBOL = "LEC";
    private static final String SEPARATOR = "|";

    public Lecture(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
        this.taskType = "E";
    }

    /**
     * Return a string to describe the lecture task.
     */
    @Override
    public String toString() {
        return "[LEC]" + super.toString() + " (" + date + " " + time + ")";
    }

    @Override
    public String printIntoFile() {
        return LECTURE_FILE_SYMBOL + SEPARATOR + isDone + SEPARATOR + description
                + SEPARATOR + this.date + SEPARATOR + this.time;
    }

    /**
     * Returns the respective task type.
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

}
