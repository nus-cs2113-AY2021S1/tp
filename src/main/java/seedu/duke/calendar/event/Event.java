package seedu.duke.calendar.event;

import seedu.duke.calendar.CalendarItem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event in the event list.
 */
public abstract class Event extends CalendarItem {
    protected LocalDate date;
    protected LocalTime time;
    protected String venue;
    protected boolean isOver;

    public Event(LocalDate date, LocalTime time, String venue) {
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a"))
                + " (" + venue + ")";
    }

    public abstract String getTaskType();

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public boolean isOver() {
        return isOver;
    }
}
