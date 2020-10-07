package seedu.duke.data.timetable;

import java.util.ArrayList;

/**
 * Represents a TimeTable object. Contains all the events.
 */
public class Timetable {

    private ArrayList<Event> events;

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

    }

    /**
     * Removes an event from the timetable.
     *
     * @param event to be removed.
     */
    public void deleteEvent(Event event) {

    }


}
