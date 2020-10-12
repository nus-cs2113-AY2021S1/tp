package seedu.duke.data.timetable;

import seedu.duke.ui.InterfaceManager;
import seedu.duke.util.DateComparator;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Represents an Event. Contains all the information of an Event.
 */
public class Event implements Comparable<Event> {
    public static final String REMINDER_DAY = "day";
    public static final String REMINDER_WEEK = "week";

    private String title;
    // Can be combined into LocalDateTime
    private LocalDate date;
    private LocalTime time;
    private Boolean isToRemind;
    private Boolean isRecurring;
    private HashMap<String, ArrayList<Integer>> reminderPeriods = new HashMap<>();

    /**
     * Creates an Event object with its title, date and time provided.
     *
     * @param title Title of the event.
     * @param date Date of the event.
     * @param time Time of the event.
     */
    public Event(String title, LocalDate date, LocalTime time) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.isToRemind = false;
        this.isRecurring = false;
    }

    /**
     * Creates an Event object with its title, date, time, isToRemind and isRecurring provided.
     *
     * @param title Title of the event.
     * @param date Date of the event.
     * @param time Time of the event.
     * @param isToRemind Whether the event requires a reminder.
     * @param isRecurring Whether the event will re-occur.
     */
    public Event(String title, LocalDate date, LocalTime time, boolean isToRemind, boolean isRecurring) {
        this(title, date, time);
        this.isToRemind = isToRemind;
        this.isRecurring = isRecurring;
    }

    /**
     * Creates an Event object with its title, date, time, isToRemind, isRecurring as well as time before reminder and
     * time unit provided.
     *
     * @param title Title of the event.
     * @param date Date of the event.
     * @param time Time of the event.
     * @param isToRemind Whether event is set to remind.
     * @param isRecurring Whether event is set to re-occur.
     * @param timePeriods Time periods to remind about this event before it occurs.
     * @param timeUnits Time units to remind about this event before it occurs.
     */
    public Event(String title, LocalDate date, LocalTime time, boolean isToRemind, boolean isRecurring,
                 ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        this(title, date, time);
        this.isToRemind = isToRemind;
        this.isRecurring = isRecurring;

        assert timePeriods.size() == timeUnits.size() : "Something is wrong with the parser! Check it out.";

        for (int i = 0; i < timePeriods.size(); i++) {
            String timeUnit = timeUnits.get(i);
            ArrayList<Integer> storedReminders = reminderPeriods.get(timeUnit);
            if (storedReminders == null) {
                storedReminders = new ArrayList<>();
            }
            storedReminders.add(timePeriods.get(i));
            Collections.sort(storedReminders);
            reminderPeriods.put(timeUnit, storedReminders);
        }

    }

    public Event(String title, LocalDate date, LocalTime time, boolean isToRemind, boolean isRecurring,
                 HashMap<String, ArrayList<Integer>> reminderPeriods) {
        this(title, date, time, isToRemind, isRecurring);
        this.reminderPeriods = reminderPeriods;
    }

    public Event(String title, LocalDateTime dateTime, boolean isToRemind, boolean isRecurring,
                  HashMap<String, ArrayList<Integer>> reminderPeriods) {
        this(title, dateTime.toLocalDate(), dateTime.toLocalTime(), isToRemind, isRecurring, reminderPeriods);
    }

    /**
     * Creates an Event object with its title, dateTime, isToRemind and isRecurring provided. Used for event
     * constructing with user input. Has default reminder time of 1 day before event.
     *
     * @param title Title of event.
     * @param dateTime LocalDateTime provided by user input.
     * @param isToRemind Whether event is set to remind.
     * @param isRecurring Whether event is set to re-occur.
     */
    public Event(String title, LocalDateTime dateTime, boolean isToRemind, boolean isRecurring) {
        this(title, dateTime.toLocalDate(), dateTime.toLocalTime(), isToRemind, isRecurring);
    }

    /**
     * Creates an Event object with its title, dateTime, isToRemind, isRecurring as well as time before reminder and
     * the time unit provided. Used for event construction with user input.
     * @param title Title of event.
     * @param dateTime LocalDateTime provided by user input.
     * @param isToRemind Whether event is set to remind.
     * @param isRecurring Whether event is set to re-occur.
     * @param timePeriods Time periods to remind about this event before it occurs.
     * @param timeUnits Time units to remind about this event before it occurs.
     */
    public Event(String title, LocalDateTime dateTime, boolean isToRemind, boolean isRecurring,
                 ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        this(title, dateTime.toLocalDate(), dateTime.toLocalTime(), isToRemind, isRecurring, timePeriods, timeUnits);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean getToRemind() {
        return isToRemind;
    }

    public void setToRemind(boolean toRemind) {
        isToRemind = toRemind;
    }

    public HashMap<String, ArrayList<Integer>> getReminderPeriod() {
        return this.reminderPeriods;
    }

    public boolean getRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public ArrayList<LocalDate> getReminderDates() {
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (String unit : reminderPeriods.keySet()) {
            ArrayList<Integer> timePeriodsInUnit = reminderPeriods.get(unit);
            LocalDate date = this.date;
            for (Integer timePeriod : timePeriodsInUnit) {
                switch (unit) {
                case REMINDER_DAY:
                    date = date.plusDays(-timePeriod);
                    break;
                case REMINDER_WEEK:
                    date = date.plusWeeks(-timePeriod);
                    break;
                default:
                    break;
                }
                dates.add(date);
            }
        }
        dates.sort(new DateComparator());
        return dates;
    }

    public String toReminderString() {
        String titleString = "Event: " + title;
        String dateString = "Date: " + date.toString() + "\tTime: " + time.toString();
        return titleString + InterfaceManager.LS + dateString;
    }

    @Override
    public String toString() {
        String titleString = "Event: " + title;
        String dateString = "Date: " + date.toString() + "\tTime: " + time.toString();
        String remindString = "Reminder: " + isToRemind;
        String repeatingString = "Repeating: " + isRecurring;
        String lineSeparator = InterfaceManager.LS;
        return titleString + lineSeparator + dateString + lineSeparator + remindString
                + lineSeparator + repeatingString;
    }

    @Override
    public int compareTo(Event o) {
        int comp = date.compareTo(o.date);
        if (comp != 0) {
            return comp;
        } else {
            comp = time.compareTo(o.time);
            if (comp != 0) {
                return comp;
            } else {
                return title.compareTo(o.title);
            }
        }
    }
}
