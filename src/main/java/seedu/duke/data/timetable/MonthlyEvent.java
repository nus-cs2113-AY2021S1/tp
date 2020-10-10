package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalTime;

public class MonthlyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    public MonthlyEvent(String title, LocalDate date, LocalTime time, boolean isToRemind) {
        super(title, date, time, isToRemind);
    }

    @Override
    public RecurringEvent stepOneTimePeriod() {
        LocalDate date = timeStep(getDate());
        return new MonthlyEvent(getTitle(), date, getTime(), getToRemind());
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusMonths(TIME_STEP);
    }
}
