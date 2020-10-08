package seedu.duke.calendar.event;


import seedu.duke.calendar.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Activity extends Task {
    protected LocalDate at;
    protected String taskType;

    private static final String EVENT_FILE_SYMBOL = "E";
    private static final String SEPARATOR = "|";

    public Activity(String description, LocalDate at) {
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

    @Override
    public String printIntoFile() {
        return EVENT_FILE_SYMBOL + SEPARATOR + isDone + SEPARATOR + description + SEPARATOR + this.at;
    }

    @Override
    public LocalDate getTime() {
        return this.at;
    }

}
