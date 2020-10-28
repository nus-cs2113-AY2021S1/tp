package seedu.notus.data.timetable;

import seedu.notus.data.tag.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

//@@author brandonywl
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
     * @param reminderSchedule Reminder Schedule of when reminder should be provided for this event.
     */
    public DailyEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrence,
                      HashMap<String, ArrayList<Integer>> reminderSchedule, ArrayList<Tag> tags) {
        super(title, dateTime, isToRemind, endRecurrence, RecurringEvent.DAILY_RECURRENCE_TYPE,
                reminderSchedule, tags);
    }

    /**
     * Constructor of DailyEvent with a specified recurrence type and default end time of recurrence (Year 3000).
     *
     * @param title Title of Event
     * @param dateTime DateTime of Event
     * @param isToRemind Whether the Event requires reminders.
     * @param reminderSchedule Reminder Schedule of when reminder should be provided for this event.
     */
    public DailyEvent(String title, LocalDateTime dateTime, boolean isToRemind,
                      HashMap<String, ArrayList<Integer>> reminderSchedule, ArrayList<Tag> tags) {
        super(title, dateTime, isToRemind, RecurringEvent.DAILY_RECURRENCE_TYPE, reminderSchedule, tags);
    }



    @Override
    public LocalDate timeStep(LocalDate date) {
        return date.plusDays(TIME_STEP);
    }
}
