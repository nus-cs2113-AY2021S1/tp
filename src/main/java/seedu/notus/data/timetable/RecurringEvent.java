package seedu.notus.data.timetable;

import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

//@@author brandonywl
public abstract class RecurringEvent extends Event {
    private LocalDate endRecurrenceDate;
    private LocalTime endRecurrenceTime;
    private String recurrenceType;

    public static final LocalDate DEFAULT_END_RECURRENCE = LocalDate.of(3000, 12, 31);
    public static final LocalTime DEFAULT_END_RECURRENCE_TIME = LocalTime.of(23, 59);
    public static final String NO_RECURRENCE_TYPE = "none";
    public static final String DAILY_RECURRENCE_TYPE = "daily";
    public static final String WEEKLY_RECURRENCE_TYPE = "weekly";
    public static final String MONTHLY_RECURRENCE_TYPE = "monthly";
    public static final String YEARLY_RECURRENCE_TYPE = "yearly";

    /**
     * Constructor to create a recurring event. Default endRecurrenceDate is forever unless specified.
     *
     * @param title Title of Event
     * @param startDateTime Start DateTime of Event
     * @param endDateTime End DateTime of Event
     * @param isToRemind Whether the Event requires reminders.
     * @param endRecurrenceDate When the Event should stop re-occurring.
     * @param recurrenceType String representation of what type of recurrence this event is.
     * @param reminderSchedule Reminder Schedule of when reminder should be provided for this event.
     * @param tags Tags linked to the event
     */
    public RecurringEvent(String title, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isToRemind,
                          LocalDate endRecurrenceDate, String recurrenceType,
                          HashMap<String,ArrayList<Integer>> reminderSchedule, ArrayList<Tag> tags) {
        super(title, startDateTime, endDateTime, isToRemind, true, reminderSchedule, tags);
        if (endRecurrenceDate == null) {
            endRecurrenceDate = DEFAULT_END_RECURRENCE;
        }
        this.endRecurrenceDate = endRecurrenceDate;
        this.endRecurrenceTime = DEFAULT_END_RECURRENCE_TIME;
        this.recurrenceType = recurrenceType;
    }

    /**
     * An overloaded constructor which provides a default value for endRecurrenceDate.
     *
     * @param title Title of Event
     * @param startDateTime Start DateTime of Event
     * @param endDateTime End DateTime of Event
     * @param isToRemind Whether the Event requires reminders.
     * @param recurrenceType String representation of what type of recurrence this event is.
     * @param reminderSchedule Reminder Schedule of when reminder should be provided for this event.
     * @param tags Tags linked to the event
     */
    public RecurringEvent(String title, LocalDateTime startDateTime, LocalDateTime endDateTime,
                          boolean isToRemind, String recurrenceType,
                          HashMap<String, ArrayList<Integer>> reminderSchedule, ArrayList<Tag> tags) {
        this(title, startDateTime, endDateTime, isToRemind,
                DEFAULT_END_RECURRENCE, recurrenceType, reminderSchedule, tags);
    }

    public String getRecurrenceType() {
        return recurrenceType;
    }

    public LocalDate getEndRecurrenceDate() {
        return endRecurrenceDate;
    }

    public String getEndRecurrenceDateTime() {
        return endRecurrenceDate.toString() + " " + endRecurrenceTime.toString();
    }

    public void setEndRecurrenceDate(LocalDate endRecurrenceDate) {
        this.endRecurrenceDate = endRecurrenceDate;
    }

    /**
     * Checks if the event should still re-occur at a specified date.
     *
     * @param date Date to check.
     * @return Whether to re-occur or not.
     */
    public boolean checkAfterEndRecurrence(LocalDate date) {
        return (endRecurrenceDate.compareTo(date) < 0);
    }

    /**
     * Checks whether the current RecurringEvent object will re-occur between the start and end date (inclusive)
     * and return an ArrayList that contains an event for all the times that it re-occurs.
     *
     * @param startDate Start date of the time period to check (inclusive)
     * @param endDate End date of the time period to check (inclusive)
     * @return ArrayList of this event at different dates at a specified interval within the time period.
     */
    public ArrayList<Event> getRecurrences(LocalDate startDate, LocalDate endDate) {
        ArrayList<Event> eventSet = new ArrayList<>();
        while (startDate.compareTo(endDate) <= 0) {
            if (checkAfterEndRecurrence(startDate)) {
                return eventSet;
            }
            if (toReoccur(startDate)) {
                LocalDateTime dateTime = LocalDateTime.of(startDate, getStartTime());
                LocalDateTime endDateTime = dateTime.withHour(getEndTime().getHour());
                endDateTime = endDateTime.withMinute(getEndTime().getMinute());
                Event event = new Event(getTitle(), dateTime, endDateTime,
                        getIsToRemind(), false, getReminderPeriods());
                eventSet.add(event);
            }
            startDate = startDate.plusDays(1);
        }
        return eventSet;
    }

    /**
     * Check if the event is to reoccur on a specific date.
     *
     * @param date Date to check.
     * @return Whether it will reoccur
     */
    public boolean toReoccur(LocalDate date) {
        LocalDate eventDate = getStartDate();
        LocalDate finalDate = getStartDate();
        int steps = 1;
        while (finalDate.compareTo(date) < 0) {
            finalDate = timeStep(eventDate, steps);
            steps++;
        }
        return finalDate.equals(date);
    }

    @Override
    public String toString() {
        String endRecurrenceString = "Until: ";
        if (!endRecurrenceDate.equals(DEFAULT_END_RECURRENCE)) {
            endRecurrenceString = endRecurrenceString.concat(endRecurrenceDate.toString());
        } else {
            endRecurrenceString = endRecurrenceString.concat("Forever");
        }
        return super.toString() + String.format(" (%s)", recurrenceType) + Formatter.LS + endRecurrenceString;
    }

    public String getEndRecurrenceString() {
        String endRecurrenceString = "Until: ";
        String timing = (!endRecurrenceDate.equals(DEFAULT_END_RECURRENCE)) ? endRecurrenceDate.toString() : "Forever";
        endRecurrenceString = endRecurrenceString.concat(timing);
        return String.format(" (%s)", endRecurrenceString);
    }

    /**
     * Provides a time step of a specified date by one time unit and return it as a LocalDate object.
     *
     * @param date Date to step forward
     * @return Future date
     */
    public abstract LocalDate timeStep(LocalDate date);

    /**
     * Provides time steps of a specified date by a specified number of time units and return it as a LocalDate object.
     *
     * @param date Date to step forward
     * @param steps Steps to take.
     * @return Future date
     */
    public abstract LocalDate timeStep(LocalDate date, int steps);
}
