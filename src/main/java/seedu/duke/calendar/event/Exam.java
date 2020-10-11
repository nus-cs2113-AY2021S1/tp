package seedu.duke.calendar.event;

import seedu.duke.calendar.event.SchoolEvent;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an Exam event.
 */
public class Exam extends SchoolEvent {
    protected String taskType;

    private static final String EXAM_FILE_SYMBOL = "EXAM";
    private static final String SEPARATOR = "|";

    public Exam(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(moduleCode, date, time, venue);
    }


    /**
     * Returns a [Ex] icon to indicate task as a Exam task.
     */
    @Override
    public String toString() {
        return "[Exam] " + super.toString();
    }

    @Override
    public String getType() {
        return taskType;
    }

    @Override
    public String printIntoFile() {
        return EXAM_FILE_SYMBOL + SEPARATOR + isOver + SEPARATOR
                + moduleCode + SEPARATOR + date + SEPARATOR + time + SEPARATOR + venue;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public LocalTime getTime() {
        return null;
    }

}
