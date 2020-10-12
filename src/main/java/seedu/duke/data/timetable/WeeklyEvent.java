package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Subclass of RecurringEvent that re-occurs every week.
 */
public class WeeklyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    public WeeklyEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrence,
                       ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        super(title, dateTime, isToRemind, endRecurrence, RecurringEvent.WEEKLY_RECURRENCE_TYPE,
                timePeriods, timeUnits);
    }

    public WeeklyEvent(String title, LocalDateTime dateTime, boolean isToRemind,
                       ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        super(title, dateTime, isToRemind, RecurringEvent.WEEKLY_RECURRENCE_TYPE, timePeriods, timeUnits);
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusWeeks(TIME_STEP);
    }
}
