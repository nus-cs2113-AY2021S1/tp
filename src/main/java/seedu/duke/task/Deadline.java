package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected String taskType;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    /**
     * Returns a [D] icon to indicate task as a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + ")";
    }

    @Override
    /** Returns the respective task type. */
    public String getTaskType() {
        return taskType;
    }
}
