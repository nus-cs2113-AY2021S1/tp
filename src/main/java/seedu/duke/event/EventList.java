package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Event> checkEventsInTimeRange(LocalDate startDate, LocalDate endDate,
                                                   LocalTime startTime, LocalTime endTime) {
        ArrayList<Event> eventsInTimeRange = new ArrayList<>();

        for (Event event : events) {
            boolean eventIsBetweenDate = event.date.isAfter(startDate) && event.date.isBefore(endDate);

            boolean eventIsBetweenTime;
            if (eventIsBetweenDate) {
                eventIsBetweenTime = true;
            } else if (event.date.isEqual(startDate)) {
                eventIsBetweenTime = !(event.time.isBefore(startTime));
            } else if (event.date.isEqual(endDate)) {
                eventIsBetweenTime = !(event.time.isAfter(endTime));
            } else {
                eventIsBetweenTime = false;
            }

            if (eventIsBetweenTime) {
                eventsInTimeRange.add(event);
            }
        }

        return eventsInTimeRange;
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
