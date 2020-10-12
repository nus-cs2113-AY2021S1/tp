package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Subclass of RecurringEvent that re-occurs every day.
 */
public class DailyEvent extends RecurringEvent {
    private static final int TIME_STEP = 1;

    /**
     * Constructor of DailyEvent with a specified recurrence type and specified endRecurrence date.
     *
     * @param title Title of Event
     * @param dateTime DateTime of Event
     * @param isToRemind Whether the Event requires reminders.
     * @param endRecurrence When the Event should stop re-occurring.
     */
    public DailyEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrence,
                      ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        super(title, dateTime, isToRemind, endRecurrence, RecurringEvent.DAILY_RECURRENCE_TYPE,
                timePeriods, timeUnits);
    }

    /**
     * Constructor of DailyEvent with a specified recurrence type and default end time of recurrence (Year 3000).
     *
     * @param title Title of Event
     * @param dateTime DateTime of Event
     * @param isToRemind Whether the Event requires reminders.
     */
    public DailyEvent(String title, LocalDateTime dateTime, boolean isToRemind,
                      ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        super(title, dateTime, isToRemind, RecurringEvent.DAILY_RECURRENCE_TYPE, timePeriods, timeUnits);
    }



    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusDays(TIME_STEP);
    }
}
