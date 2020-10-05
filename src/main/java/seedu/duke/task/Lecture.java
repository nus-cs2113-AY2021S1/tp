package seedu.duke.task;

/**
 * Represents a lecture event.
 */
public class Lecture extends Task {
    protected String date;
    protected String time;
    protected String taskType;

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

    /** Returns the respective task type. */
    @Override
    public String getTaskType() {
        return taskType;
    }

}
