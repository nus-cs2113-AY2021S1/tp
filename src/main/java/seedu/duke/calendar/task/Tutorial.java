package seedu.duke.calendar.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a tutorial event.
 */
public class Tutorial extends Task {
    protected String date;
    protected String time;
    protected String taskType;

    private static final String TUTORIAL_FILE_SYMBOL = "TUT";
    private static final String SEPARATOR = "|";


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

    @Override
    public String printIntoFile() {
        return TUTORIAL_FILE_SYMBOL + SEPARATOR + isDone + SEPARATOR + description
                + SEPARATOR + this.date + SEPARATOR + this.time;
    }

    /**
     * Returns the respective task type.
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public LocalTime getTime() {
        return null;
    }
}
