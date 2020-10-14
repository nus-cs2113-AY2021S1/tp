package seedu.duke.calendar.event;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an Exam event.
 */
public class Exam extends SchoolEvent {
    protected String eventType;

    private static final String EXAM_FILE_SYMBOL = "EXAM";
    private static final String SEPARATOR = "|";
    public static final String TICK_SYMBOL = "/";
    public static final String CROSS_SYMBOL = "X";

    public Exam(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(moduleCode, date, time, venue);
    }

    /**
     * Check whether the exam is over.
     *
     * @return whether the exam is over
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
     * Show whether the exam is over.
     *
     * @return whether the exam is over
     */
    public String getIcon() {
        return (getIsOver() ? TICK_SYMBOL : CROSS_SYMBOL);
    }

    /**
     * Returns a [Ex] icon to indicate task as a Exam task.
     */
    @Override
    public String toString() {
        return "[Exam][" + getIcon() + "] " + super.toString();
    }

    @Override
    public String getType() {
        return eventType;
    }

    @Override
    public String printIntoFile() {
        return EXAM_FILE_SYMBOL + SEPARATOR + isOver + SEPARATOR
                + moduleCode + SEPARATOR + date + SEPARATOR + time + SEPARATOR + venue;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public LocalTime getTime() {
        return this.time;
    }

}
