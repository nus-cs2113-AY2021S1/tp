package seedu.duke.event;

import seedu.duke.EventLogger;
import seedu.duke.exception.InvalidIndexException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EventList {
    private String name;
    private ArrayList<Event> events;
    private static Logger logger = EventLogger.getEventLogger();

    public EventList(String name) {
        this.name = name;
        events = new ArrayList<Event>();
    }

    public EventList(String name, ArrayList<Event> events) {
        this.name = name;
        this.events = new ArrayList<Event>(events);
    }

    public void add(Event event) {
        events.add(event);
    }

    public String getName() {
        return name;
    }

    public Event getEventByIndex(int index) throws InvalidIndexException {

        try {
            return events.get(index);
        } catch (IndexOutOfBoundsException e) {
            logger.warning("InvalidIndexException encountered");
            throw new InvalidIndexException("Error, no such index is available!");
        }


    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public Event getNewestEvent() {
        return events.get(events.size() - 1);

    }

    public int getSize() {
        return events.size();
    }

}
