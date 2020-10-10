package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalTime;

public class YearlyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    public YearlyEvent(String title, LocalDate date, LocalTime time, boolean isToRemind, LocalDate endRecurrance) {
        super(title, date, time, isToRemind, endRecurrance);
    }
    public YearlyEvent(String title, LocalDate date, LocalTime time, boolean isToRemind) {
        super(title, date, time, isToRemind, DEFAULT_END_RECURRANCE);
    }

    @Override
    public RecurringEvent stepOneTimePeriod() {
        LocalDate date = timeStep(getDate());
        return new YearlyEvent(getTitle(), date, getTime(), getToRemind());
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusYears(TIME_STEP);
    }
}
