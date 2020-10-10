package seedu.duke.data.timetable;

import java.time.LocalDate;
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
        this.events.add(event);
    }

    /**
     * Removes an event from the timetable.
     *
     * @param index Index to be removed.
     */
    public void deleteEvent(int index) {
        Event event = events.get(index);
        this.events.remove(index);
    }

    public int getDay() {
        LocalDate today = LocalDate.now();
        return today.getDayOfWeek().getValue();
    }

    public int getDay(LocalDate date) {
        return date.getDayOfWeek().getValue();
    }

    public ArrayList<Event> getMonthTimetable(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        ArrayList<Event> eventSet = new ArrayList<Event>();
        for (Event event : events) {

        }

        int numDays = startDate.lengthOfMonth();
        for (int i = 0; i < numDays; i++) {

        }
        return eventSet;
    }

}
