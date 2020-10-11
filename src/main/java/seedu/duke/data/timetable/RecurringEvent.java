package seedu.duke.data.timetable;

import seedu.duke.ui.InterfaceManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class RecurringEvent extends Event {
    private LocalDate endRecurrenceDate;
    private String recurrenceType;

    public static final LocalDate DEFAULT_END_RECURRENCE = LocalDate.of(3000, 12, 31);
    public static final String DAILY_RECURRENCE_TYPE = "daily";
    public static final String WEEKLY_RECURRENCE_TYPE = "weekly";
    public static final String MONTHLY_RECURRENCE_TYPE = "monthly";
    public static final String YEARLY_RECURRENCE_TYPE = "yearly";

    public RecurringEvent(String title, LocalDateTime dateTime, boolean isToRemind, LocalDate endRecurrenceDate,
                          String recurrenceType) {
        super(title, dateTime, isToRemind, true);
        if (endRecurrenceDate == null) {
            endRecurrenceDate = DEFAULT_END_RECURRENCE;
        }
        this.endRecurrenceDate = endRecurrenceDate;
        this.recurrenceType = recurrenceType;
    }

    public RecurringEvent(String title, LocalDateTime dateTime, boolean isToRemind, String recurrenceType) {
        this(title, dateTime, isToRemind, DEFAULT_END_RECURRENCE, recurrenceType);
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
        LocalTime timing = getTime();
        while (startDate.compareTo(endDate) <= 0) {
            if (checkAfterEndRecurrence(startDate)) {
                return eventSet;
            }
            LocalDateTime dateTime = LocalDateTime.of(startDate, timing);
            Event e = new Event(getTitle(), dateTime, getToRemind(), false);
            eventSet.add(e);
            startDate = timeStep(startDate);
        }
        return eventSet;
    }

    @Override
    public String toString() {
        String endRecurrenceString = "Until: ";
        if (!endRecurrenceDate.equals(DEFAULT_END_RECURRENCE)) {
            endRecurrenceString += endRecurrenceDate.toString();
        } else {
            endRecurrenceString += "Forever";
        }
        return super.toString() + String.format(" (%s)", recurrenceType) + InterfaceManager.LS + endRecurrenceString;
    }

    /**
     * Provide a time step of the current event by one time unit and return it as a immutable object.
     *
     * @return RecurringEvent with one time step forward.
     */
    public abstract RecurringEvent stepOneTimePeriod();

    /**
     * Provides a time step of a specified date by one time unit and return it as a LocalDate object.
     *
     * @param date Date to step forward
     * @return Future date
     */
    public abstract LocalDate timeStep(LocalDate date);

}
