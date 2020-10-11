package seedu.duke.calendar.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a lecture event.
 */
public class Lecture extends SchoolEvent {
    protected String eventType;

    private static final String LECTURE_FILE_SYMBOL = "LEC";
    private static final String SEPARATOR = "|";
    public static final String TICK_SYMBOL = "/";
    public static final String CROSS_SYMBOL = "X";

    /**
     * A Constructor of a lecture object.
     *
     * @param moduleCode module code of the lecture
     * @param date date of the lecture
     * @param time time of the lecture
     * @param venue venue of the lecture
     */
    public Lecture(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(moduleCode, date, time, venue);
        eventType = "LEC";
        isOver = false;
    }

    /**
     * Check whether the lecture is over.
     *
     * @return whether the tutorial is over
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
     * Show whether the lab is over.
     *
     * @return whether the lab is over
     */
    public String getIcon() {
        return (getIsOver() ? TICK_SYMBOL : CROSS_SYMBOL);
    }

    /**
     * Describe the lecture event.
     *
     * @Return a string to describe the lecture event.
     */
    @Override
    public String toString() {

        return "[LEC]" + "[" + getIcon() + "] " + moduleCode + " "
                + date.format(DateTimeFormatter.ofPattern("dd-MM-yy E"))
                + " " + time.format(DateTimeFormatter.ofPattern("h:mma"))
                + " (" + venue + ")";
    }

    /**
     * Save the lecture event into files.
     *
     * @return string contains the information about the lecture event.
     */
    @Override
    public String printIntoFile() {
        return LECTURE_FILE_SYMBOL + SEPARATOR + isOver + SEPARATOR + moduleCode
                + SEPARATOR + this.date + SEPARATOR + this.time + SEPARATOR + venue;
    }

    /**
     * Get the date of the lecture.
     *
     * @return date of the lecture
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return "[LEC]" + "[" + getIcon() + "] " + moduleCode + " "
                + " (" + venue + ")";
    }

    @Override
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns the respective object type.
     */
    @Override
    public String getType() {
        return eventType;
    }

}
