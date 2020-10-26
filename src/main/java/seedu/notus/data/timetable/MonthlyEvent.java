package seedu.notus.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

//@@author brandonywl
/**
 * Subclass of RecurringEvent that re-occurs every month.
 */
public class MonthlyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    /**
     * Constructor of MonthlyEvent with a specified recurrence type and specified endRecurrence date.
     *
     * @param title Title of Event
     * @param dateTime DateTime of Event
     * @param isToRemind Whether the Event requires reminders.
     * @param endRecurrence When the Event should stop re-occurring.
     * @param timePeriods Number of units of time before an event for a reminder.
     * @param timeUnits Units of time before an event for a reminder. (Day, Week)
     */
    public MonthlyEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrence,
                        ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        super(title, dateTime, isToRemind, endRecurrence, RecurringEvent.MONTHLY_RECURRENCE_TYPE,
                timePeriods, timeUnits);
    }

    /**
     * Constructor of MonthlyEvent with a specified recurrence type and default end time of recurrence (Year 3000).
     *
     * @param title Title of Event
     * @param dateTime DateTime of Event
     * @param isToRemind Whether the Event requires reminders.
     * @param timePeriods Number of units of time before an event for a reminder.
     * @param timeUnits Units of time before an event for a reminder. (Day, Week)
     */
    public MonthlyEvent(String title, LocalDateTime dateTime, boolean isToRemind,
                        ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        super(title, dateTime, isToRemind, RecurringEvent.MONTHLY_RECURRENCE_TYPE, timePeriods, timeUnits);
    }

    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusMonths(TIME_STEP);
    }
}
