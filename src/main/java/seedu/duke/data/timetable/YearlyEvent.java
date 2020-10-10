package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    public YearlyEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrance) {
        super(title, dateTime, isToRemind, endRecurrance, RecurringEvent.YEARLY_RECURRENCE);
    }

    public YearlyEvent(String title, LocalDateTime dateTime, boolean isToRemind) {
        super(title, dateTime, isToRemind, DEFAULT_END_RECURRENCE, RecurringEvent.YEARLY_RECURRENCE);
    }

    @Override
    public RecurringEvent stepOneTimePeriod() {
        LocalDate date = timeStep(getDate());
        return new YearlyEvent(getTitle(), LocalDateTime.of(date, getTime()), getToRemind());
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusYears(TIME_STEP);
    }
}
