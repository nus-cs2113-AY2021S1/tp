package seedu.duke.calendar.event;


import seedu.duke.calendar.task.Event;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an event task.
 */
public class Activity extends Event {
    private String details;
    protected String eventType;

    private static final String EVENT_FILE_SYMBOL = "ACT";
    private static final String SEPARATOR = "|";

    public Activity(String details, LocalDate date, LocalTime time, String venue) {
        super(date, time, venue);
        this.details = details;
        eventType = "ACT";
    }


    /**
     * Returns a [A] icon to indicate task as a event task.
     */
    @Override
    public String toString() {
        return "[A] " + details + " " + super.toString();
    }

    @Override
    /** Returns the respective event type. */
    public String getType() {
        return eventType;
    }

    @Override
    public String printIntoFile() {
        return EVENT_FILE_SYMBOL + SEPARATOR + isOver + SEPARATOR + details
                + SEPARATOR + this.date + SEPARATOR + this.time + SEPARATOR + venue;
    }

    @Override
    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getDescription() {
        return details;
    }

}
