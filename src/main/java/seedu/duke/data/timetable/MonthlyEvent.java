package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    public MonthlyEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrance) {
        super(title, dateTime, isToRemind, endRecurrance, RecurringEvent.MONTHLY_RECURRENCE);
    }

    public MonthlyEvent(String title, LocalDateTime dateTime, boolean isToRemind) {
        super(title, dateTime, isToRemind, RecurringEvent.MONTHLY_RECURRENCE);
    }

    @Override
    public RecurringEvent stepOneTimePeriod() {
        LocalDate date = timeStep(getDate());
        return new MonthlyEvent(getTitle(), LocalDateTime.of(date, getTime()), getToRemind());
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusMonths(TIME_STEP);
    }
}
