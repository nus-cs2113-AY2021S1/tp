package seedu.duke.event;

import java.util.ArrayList;

public class EventList {
    private String name;
    private ArrayList<Event> events;

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

    public Event getEventByIndex(int index) {

        try {

            return events.get(index);

        } catch (IndexOutOfBoundsException e) {

            System.out.println("Error, no such index is available!");

        }
        //event not found
        return null;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public Event getNewestEvent() {
        return events.get(events.size() - 1);

    }

}
