package seedu.duke.calendar.event;

import seedu.duke.calendar.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a tutorial event.
 */
public class Tutorial extends SchoolEvent {
    protected String eventType;

    private static final String TUTORIAL_FILE_SYMBOL = "TUT";
    private static final String SEPARATOR = "|";

    /**
     * A Constructor of a tutorial object.
     *
     * @param moduleCode module code of the tutorial
     * @param date date of the tutorial
     * @param time time of the tutorial
     * @param venue venue of the tutorial
     */
    public Tutorial(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(moduleCode, date, time, venue);
        eventType = "TUT";
    }


    /**
     * Describe the tutorial event.
     *
     * @Return a string to describe the tutorial event.
     */
    @Override
    public String toString() {
        return "[TUT]" +  moduleCode + " " + date.format(DateTimeFormatter.ofPattern("dd-MM-yy E")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mma"))
                + " (" + venue + ")";
    }

    /**
     * Save the tutorial event into files.
     *
     * @return string contains the information about the tutorial event.
     */
    @Override
    public String printIntoFile() {
        return TUTORIAL_FILE_SYMBOL + " " + moduleCode
                + SEPARATOR + this.date + SEPARATOR + this.time;
    }

    /**
     * Returns the respective type.
     */
    @Override
    public String getType() {
        return eventType;
    }

    /**
     * Get the date of the tutorial.
     *
     * @return date of the tutorial
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get the time of the tutorial.
     *
     * @return time of the tutorial
     */
    @Override
    public LocalTime getTime() {
        return time;
    }
}
