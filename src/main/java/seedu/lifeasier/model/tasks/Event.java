package seedu.lifeasier.model.tasks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
    private static final String PARAM_EVENT = "event";

    protected LocalDateTime start;
    protected LocalDateTime end;
    private String type = PARAM_EVENT;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, LocalDateTime start, LocalDateTime end, int recurrences) {
        super(description);
        this.start = start;
        this.end = end;
        this.recurrences = recurrences;
    }

    public Event(Task task, int editNumber) {
        super(task, editNumber);
        this.start = ((Event) task).start;
        this.end = ((Event) task).end;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public LocalDateTime getStart() {
        return start;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    @Override
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Event: " + super.toString() + " (" + start.format(format) + " to " +  end.format(format) + "), "
                + "repeats weekly " + recurrences + " times";
    }

    /**
     * Moves a recurring event 7 days forward, and decrements remaining recurrences by 1.
     */
    @Override
    public void moveAndUpdateRecurrences(LocalDate day) {
        while (this.start.toLocalDate().isBefore(day)) {
            decrementRecurrences(1);
            start = start.plusDays(7);
            end = end.plusDays(7);
        }
    }

    /**
     * Returns true if a similar or duplicate Event already exists in the schedule.
     *
     * @param description Description of Event to be checked.
     * @param start Start DateTime of Event to be checked.
     * @param end End DateTime of Event to be checked.
     * @param recurrences Recurrences of Event to be checked.
     * @return True if a similar or duplicate Event already exists in the schedule.
     */
    public boolean isDuplicate(String description, LocalDateTime start, LocalDateTime end, int recurrences) {

        LocalTime existingStartTime = this.start.toLocalTime();
        LocalTime existingEndTime = this.end.toLocalTime();
        DayOfWeek existingDay = this.start.getDayOfWeek();

        LocalTime newStartTime = start.toLocalTime();
        LocalTime newEndTime = end.toLocalTime();
        DayOfWeek newDay = start.getDayOfWeek();

        return (this.description.equals(description)
                && existingStartTime == newStartTime
                && existingEndTime == newEndTime
                && existingDay == newDay
                && ((this.recurrences > 0 && recurrences > 0) || this.start.equals(start)));
    }
}
