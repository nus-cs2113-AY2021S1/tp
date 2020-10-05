package seedu.duke.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDate at;
    protected String taskType;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.taskType = "E";
    }

    /**
     * Returns a [E] icon to indicate task as a event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + ")";
    }

    @Override
    /** Returns the respective task type. */
    public String getTaskType() {
        return taskType;
    }

}
