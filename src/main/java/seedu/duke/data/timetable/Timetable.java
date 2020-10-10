package seedu.duke.data.timetable;

import seedu.duke.data.exception.SystemException;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Represents a TimeTable object. Contains all the events.
 */
public class Timetable {

    private ArrayList<Event> events;
    private HashMap<Integer, ArrayList<Event>> thisYearTimetable;
    private ArrayList<Event> nonRecurringEvents;
    private ArrayList<DailyEvent> dailyEvents;
    private ArrayList<WeeklyEvent> weeklyEvents;
    private ArrayList<MonthlyEvent> monthlyEvents;
    private ArrayList<YearlyEvent> yearlyEvents;

    /**
     * Creates a new list of events.
     */
    public Timetable() {
        events = new ArrayList<>();
        nonRecurringEvents = new ArrayList<>();
        dailyEvents = new ArrayList<>();
        weeklyEvents = new ArrayList<>();
        monthlyEvents = new ArrayList<>();
        yearlyEvents = new ArrayList<>();
    }

    /**
     * Creates a list of events from existing data.
     *
     * @param events from existing data.
     */
    public Timetable(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * Adds an event into the timetable.
     *
     * @param event to be added.
     */
    public void addEvent(Event event) {
        events.add(event);
        if (!event.getRecurring()) {
            nonRecurringEvents.add(event);
        } else {
            if (event instanceof DailyEvent) {
                dailyEvents.add((DailyEvent) event);
            } else if (event instanceof WeeklyEvent) {
                weeklyEvents.add((WeeklyEvent) event);
            } else if (event instanceof MonthlyEvent) {
                monthlyEvents.add((MonthlyEvent) event);
            } else if (event instanceof YearlyEvent) {
                yearlyEvents.add((YearlyEvent) event);
            }
        }
    }

    /**
     * Removes an event from the timetable.
     *
     * @param index Index to be removed.
     */
    public Event deleteEvent(int index) throws SystemException {
        if (index >= events.size() || index < 0) {
            throw new SystemException(SystemException.ExceptionType.EXCEPTION_INDEX_OUT_OF_RANGE);
        }
        Event event = events.get(index);
        events.remove(index);
        if (event instanceof DailyEvent) {
            dailyEvents.remove((DailyEvent) event);
        } else if (event instanceof WeeklyEvent) {
            weeklyEvents.remove((WeeklyEvent) event);
        } else if (event instanceof MonthlyEvent) {
            monthlyEvents.remove((MonthlyEvent) event);
        } else if (event instanceof YearlyEvent) {
            yearlyEvents.remove((YearlyEvent) event);
        } else {
            nonRecurringEvents.remove(event);
        }
        return event;
    }

    public int getDay() {
        LocalDate today = LocalDate.now();
        return today.getDayOfWeek().getValue();
    }

    public int getDay(LocalDate date) {
        return date.getDayOfWeek().getValue();
    }

    /**
     * Gets the timetable for a specified month and year. Includes multiple recurrent events.
     * @param year Year to check for timetable
     * @param month Month to check for timetable
     * @return A HashMap that maps the date to an ArrayList of all events that occurs on the day.
     */
    public HashMap<Integer, ArrayList<Event>> getMonthTimetable(int year, int month) {
        HashMap<Integer, ArrayList<Event>> monthTimetable = new HashMap<>();
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        ArrayList<Event> eventSet = new ArrayList<>();
        eventSet.addAll(getAllRecurringEvents(startDate, endDate, dailyEvents));
        eventSet.addAll(getAllRecurringEvents(startDate, endDate, weeklyEvents));
        eventSet.addAll(getAllRecurringEvents(startDate, endDate, monthlyEvents));
        eventSet.addAll(getAllRecurringEvents(startDate, endDate, yearlyEvents));
        eventSet.addAll(nonRecurringEvents);

        int numDays = startDate.lengthOfMonth();
        for (int i = 1; i <= numDays; i++) {
            monthTimetable.put(i, new ArrayList<Event>());
        }

        for (Event event : eventSet) {
            int date = event.getDate().getDayOfMonth();
            ArrayList<Event> eventList = monthTimetable.get(date);
            eventList.add(event);
            monthTimetable.put(date, eventList);
        }
        return monthTimetable;
    }

    /**
     * Given an ArrayList of events, get all events that will be occur will occur during the specified time period.
     * @param startDate Start date of the period to check for recurrence. Inclusive of the date.
     * @param endDate End date of the period to check for recurrence. Inclusive of the date.
     * @param events List of events that are recurrent.
     * @return An ArrayList of Events of all events that will occur between the two specified time periods.
     */
    public ArrayList<Event> getAllRecurringEvents(LocalDate startDate, LocalDate endDate,
                                                  ArrayList<? extends RecurringEvent> events) {
        ArrayList<Event> eventList = new ArrayList<>();
        for (RecurringEvent event : events) {
            eventList.addAll(event.getRecurrences(startDate, endDate));
        }
        return eventList;
    }
}
