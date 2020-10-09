package seedu.duke.calendar.event;

import seedu.duke.calendar.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a lab event.
 */
public class Lab extends SchoolEvent {
    protected String taskType;

    private static final String LAB_FILE_SYMBOL = "LAB";
    private static final String SEPARATOR = "|";

    /**
     * A Constructor of a lab object.
     *
     * @param moduleCode module code of the lab
     * @param date date of the lab
     * @param time time of the lab
     * @param venue venue of the lab
     */
    public Lab(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(moduleCode, date, time, venue);
        taskType = "LAB";
    }

    /**
     * Describe the lab event
     *
     * @return a string containing the information about the lab event
     */
    @Override
    public String toString() {
        return "[LAB] " + moduleCode + " " + date.format(DateTimeFormatter.ofPattern("dd-MM-yy E")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mma"))
                + " (" + venue + ")";
    }

    /**
     * Save the lab event into files.
     *
     * @return string contains the information about the lab event.
     */
    @Override
    public String printIntoFile() {
        return LAB_FILE_SYMBOL + " " + moduleCode
                + SEPARATOR + this.date + SEPARATOR + this.time;
    }

    /**
     * Returns the respective object type.
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    /**
     * Get the date of the lab.
     *
     * @return date of the lab
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get the time of the lab.
     *
     * @return time of the lab
     */
    @Override
    public LocalTime getTime() {
        return time;
    }
}
