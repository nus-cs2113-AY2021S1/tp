package seedu.duke.calendar.event;


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
    public static final String TICK_SYMBOL = "/";
    public static final String CROSS_SYMBOL = "X";

    /**
     * A constructor of an activity object.
     *
     * @param details the description of the activity.
     * @param date date of the activity.
     * @param time time of the activity.
     * @param venue venue of the activity.
     */
    public Activity(String details, LocalDate date, LocalTime time, String venue) {
        super(date, time, venue);
        this.details = details;
        eventType = "ACT";
    }

    /**
     * Check whether the activity is over.
     *
     * @return whether the activity is over
     */

    public boolean getIsOver() {
        if (date.isBefore(LocalDate.now())) {
            return true;
        } else if (date.isEqual(LocalDate.now()) && time.isBefore(LocalTime.now())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Show whether the activity is over.
     *
     * @return whether the activity is over
     */

    public String getIcon() {
        return (getIsOver() ? TICK_SYMBOL : CROSS_SYMBOL);
    }


    /**
     * Returns a [A] icon to indicate task as a event task.
     */
    @Override
    public String toString() {
        return "[A][" + getIcon() + "] " + details + " " + super.toString();
    }

    /** Returns the respective event type. */
    @Override
    public String getType() {
        return eventType;
    }

    /**
     * Saves the activity into files.
     *
     * @return string contains the information about the activity event.
     */
    @Override
    public String printIntoFile() {
        return EVENT_FILE_SYMBOL + SEPARATOR + isOver + SEPARATOR + details
                + SEPARATOR + this.date + SEPARATOR + this.time + SEPARATOR + venue;
    }

    /**
     * Returns the activity time.
     */
    @Override
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Returns the activity date.
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the activity description.
     */
    @Override
    public String getDescription() {
        return details;
    }

}
