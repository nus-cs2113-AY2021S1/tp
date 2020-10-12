package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Subclass of RecurringEvent that re-occurs every month.
 */
public class MonthlyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    public MonthlyEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrence,
                        ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        super(title, dateTime, isToRemind, endRecurrence, RecurringEvent.MONTHLY_RECURRENCE_TYPE,
                timePeriods, timeUnits);
    }

    public MonthlyEvent(String title, LocalDateTime dateTime, boolean isToRemind,
                        ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        super(title, dateTime, isToRemind, RecurringEvent.MONTHLY_RECURRENCE_TYPE, timePeriods, timeUnits);
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusMonths(TIME_STEP);
    }
}
