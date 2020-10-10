package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalTime;

public class WeeklyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    public WeeklyEvent(String title, LocalDate date, LocalTime time, boolean isToRemind) {
        super(title, date, time, isToRemind);
    }

    @Override
    public RecurringEvent stepOneTimePeriod() {
        LocalDate date = timeStep(getDate());
        return new WeeklyEvent(getTitle(), date, getTime(), getToRemind());
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusWeeks(TIME_STEP);
    }
}
