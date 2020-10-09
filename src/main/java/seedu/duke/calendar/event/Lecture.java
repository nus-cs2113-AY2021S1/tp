package seedu.duke.calendar.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a lecture event.
 */
public class Lecture extends SchoolEvent {
    protected String taskType;

    private static final String LECTURE_FILE_SYMBOL = "LEC";
    private static final String SEPARATOR = "|";

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
        taskType = "LEC";
    }


    /**
     * Describe the lecture event.
     *
     * @Return a string to describe the lecture event.
     */
    @Override
    public String toString() {
        return "[LEC] " + moduleCode + " " + date.format(DateTimeFormatter.ofPattern("dd-MM-yy E")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mma"))
                + " (" + venue + ")";
    }

    /**
     * Save the lecture event into files.
     *
     * @return string contains the information about the lecture event.
     */
    @Override
    public String printIntoFile() {
        return LECTURE_FILE_SYMBOL + " " + moduleCode
                + SEPARATOR + this.date + SEPARATOR + this.time;
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
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns the respective object type.
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

}
