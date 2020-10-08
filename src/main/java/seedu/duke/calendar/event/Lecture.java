package seedu.duke.calendar.event;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a lecture event.
 */
public class Lecture extends SchoolEvent {

    protected String taskType;

    private static final String LECTURE_FILE_SYMBOL = "LEC";
    private static final String SEPARATOR = "|";

    public Lecture(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(moduleCode, date, time, venue);
        taskType = "LEC";
    }


    /**
     * Return a string to describe the lecture task.
     */
    @Override
    public String toString() {
        return "[LEC] " + super.toString();
    }

    @Override
    public String printIntoFile() {
        return LECTURE_FILE_SYMBOL + " " + moduleCode
                + SEPARATOR + this.date + SEPARATOR + this.time;
    }

    /**
     * Returns the respective task type.
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

}
