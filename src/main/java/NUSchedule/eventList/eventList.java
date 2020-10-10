package NUSchedule.eventList;


import NUSchedule.event.Event;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Contains the list of the events and provides ways to delete/add/mark as done events.
 */
public class EventList {
    private final ArrayList<Event> events;

    /**
     * Construct the events as an empty ArrayList.
     */
    public EventList() {
        events = new ArrayList<Event>();
    }

    /**
     * When loading from the file, create the list as provided in the file.
     *
     * @param events provided by <code>Storage::load()</code>
     * @see NUSchedule.storage.Storage
     */
    public EventList(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * Adds an event to the list.
     *
     * @param eventToBeAdded may be Todo/Event/Deadline based on the usage
     */
    public void addEvent(Event eventToBeAdded) {
        events.add(eventToBeAdded);
    }

    /**
     * Mark a event as done.
     *
     * @param eventIndex the index of the event being done
//     * @throws DoneUndefinedEventException the event is not defined but the user want to mark it as done
     */
    public void doneEvent(int eventIndex) /*throws DoneUndefinedEventException*/ {
//        try {
            events.get(eventIndex).markAsDone();
//        } catch (IndexOutOfBoundsException e) {
//            throw new DoneUndefinedEventException(eventIndex);
//        }
    }

    /**
     * Gives the access to the list of events.
     *
     * @return the <code>ArrayList<event></code> object to be printed
     */
    public ArrayList<Event> getEventList() {
        return events;
    }

    /**
     * Returns how many events are in the list.
     *
     * @return the size of the ArrayList in the EventList object
     */
    public int size() {
        return events.size();
    }

    /**
     * Remove the event indicated by the user.
     */
    public void remove(int eventIndex) {
        events.remove(eventIndex);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param eventIndex index of the event to return
     * @return the event at the specified position in this list
     */
    public Event get(int eventIndex) {
        return events.get(eventIndex);
    }

    /**
     * Filter the event list to find the events contain the information looking for by the user.
     *
     * @param filterString the keyword that the user is looking for
     * @return the filtered list. this list contains only the events that satisfy the requirement
     */
    public ArrayList<Event> filterWith(String filterString) {
        ArrayList<Event> filteredEventList = (ArrayList<Event>) events.stream()
                .filter(s -> s.getDescription().contains(filterString))
                .collect(toList());

        return filteredEventList;
    }
    /**
     * Filter the event list to find the events happen on the date looking for by the user.
     *
     * @param date the date that the user is looking for
     * @return the filtered list. this list contains only the events that satisfy the requirement
     */
    public ArrayList<Event> filterDateWith(LocalDate date) {
        ArrayList<Event> filteredEventList = (ArrayList<Event>) events.stream()
                .filter(s -> s.getDate().isEqual(date))
                .collect(toList());

        return filteredEventList;
    }
}
