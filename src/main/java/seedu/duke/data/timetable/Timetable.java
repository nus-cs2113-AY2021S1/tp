package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a TimeTable object. Contains all the events.
 */
public class Timetable {

    private ArrayList<Event> events;
    private HashMap<Integer, ArrayList<Event>> reminders;
    private ArrayList<Event> dailyEvents;

    /**
     * Creates a new list of events.
     */
    public Timetable() {
        events = new ArrayList<>();
        reminders = new HashMap<>();
        for (int i = 0; i < Calendar.DAY_OF_WEEK; i++) {
            reminders.put(i, new ArrayList<Event>());
        }
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
        if (event.getToRemind()) {
            removeReminder(event);
        }
        this.events.remove(index);
    }

    public int getDay() {
        LocalDate today = LocalDate.now();
        return today.getDayOfWeek().getValue();
    }

    public int getDay(LocalDate date) {
        return date.getDayOfWeek().getValue();
    }

    public void removeReminder(Event e) {
        int day = e.getDate().getDayOfWeek().getValue() - 1;
        List<Event> reminderList = reminders.get(day);
        reminderList.remove(e);
    }

}
