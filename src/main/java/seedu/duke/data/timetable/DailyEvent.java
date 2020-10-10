package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    public DailyEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrence) {
        super(title, dateTime, isToRemind, endRecurrence, RecurringEvent.DAILY_RECURRENCE);
    }

    public DailyEvent(String title, LocalDateTime dateTime, boolean isToRemind) {
        super(title, dateTime, isToRemind, RecurringEvent.DAILY_RECURRENCE);
    }

    @Override
    public RecurringEvent stepOneTimePeriod() {
        LocalDate date = getDate().plusDays(TIME_STEP);
        return new DailyEvent(getTitle(), LocalDateTime.of(date, getTime()), getToRemind());
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusDays(TIME_STEP);
    }
}
