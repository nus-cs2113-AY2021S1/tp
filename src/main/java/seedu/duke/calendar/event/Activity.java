package seedu.duke.calendar.event;


import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an event task.
 */
public class Activity extends Event {
    private String details;
    protected String taskType;

    private static final String EVENT_FILE_SYMBOL = "E";
    private static final String SEPARATOR = "|";

    public Activity(String details, LocalDate date, LocalTime time, String venue) {
        super(date, time, venue);
        this.details = details;
        taskType = "ACT";
    }


    /**
     * Returns a [A] icon to indicate task as a event task.
     */
    @Override
    public String toString() {
        return "[A] " + details + " " + super.toString();
    }

    @Override
    /** Returns the respective task type. */
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String printIntoFile() {
        return EVENT_FILE_SYMBOL + SEPARATOR + details + SEPARATOR + this.date;
    }

    @Override
    public LocalTime getTime() {
        return this.time;
    }

}
