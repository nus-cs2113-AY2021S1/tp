package seedu.duke.calendar.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected String taskType;
    protected int countdown;

    private static final String DEADLINE_FILE_SYMBOL = "D";
    private static final String SEPARATOR = "|";
    private static final LocalDate TODAY = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskType = "D";
        this.isImportant = getIsImportant();
        if (TODAY.isAfter(by)) {
            super.markAsDone();
            System.out.println("The deadline has already passed! Automatically marked as done!\n");
        }
    }

    /**
     * Returns a [D] icon to indicate task as a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + by.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }

    @Override
    public String getDescription() {
        return "[D]" + super.toString();
    }

    @Override
    public String getRecurringDescription() {
        return null;
    }

    @Override
    /** Returns the respective task type. */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Saves the deadline task into files.
     *
     * @return string contains the information about the deadline task.
     */
    @Override
    public String printIntoFile() {
        return DEADLINE_FILE_SYMBOL + SEPARATOR + isDone + SEPARATOR + description + SEPARATOR + this.isImportant
                + SEPARATOR + this.by;
    }

    /**
     * Returns the date of the deadline.
     */
    @Override
    public LocalDate getDate() {
        return this.by;
    }

    /**
     * Returns the time of the deadline.
     */
    @Override
    public LocalTime getTime() {
        return null;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
}
