package seedu.duke.event;

import seedu.duke.EventLogger;
import seedu.duke.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.logging.Logger;

public class EventList {
    private String name;
    private ArrayList<Event> events;
    private static Logger logger = EventLogger.getEventLogger();

    /**
     * Constructor for EventList. Creates an empty EventList with specified name.
     *
     * @param name EventList.
     */
    public EventList(String name) {
        this.name = name;
        events = new ArrayList<Event>();
    }

    /**
     * Constructor for EventList. Creates an EventList with the given ArrayList of Event with specified name.
     *
     * @param name of EventList.
     */
    public EventList(String name, ArrayList<Event> events) {
        this.name = name;
        this.events = new ArrayList<Event>(events);
    }

    /**
     * Adds given Event to the EventList.
     *
     * @param event to add.
     */
    public void add(Event event) {
        events.add(event);
    }

    /**
     * Returns the name of the EventList.
     *
     * @return name of EventList.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the event in the EventList at the specified index.
     *
     * @param index of event to return.
     * @return event at specified index.
     * @throws InvalidIndexException if index is not valid.
     */
    public Event getEventByIndex(int index) throws InvalidIndexException {

        try {
            return events.get(index);
        } catch (IndexOutOfBoundsException e) {
            logger.warning("InvalidIndexException encountered");
            throw new InvalidIndexException("Error, no such index is available!");
        }


    }

    /**
     * Returns an ArrayList of Event containing all the events in the EventList.
     *
     * @return all events in EventList.
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Returns the last event added in the EventList.
     *
     * @return last event added.
     */
    public Event getNewestEvent() {
        return events.get(events.size() - 1);

    }

    /**
     * Returns the number of events in the EventList.
     *
     * @return number of events.
     */
    public int getSize() {
        return events.size();
    }

}
