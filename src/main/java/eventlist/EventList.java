package eventlist;


import event.Event;
import exception.EmptyEventListException;
import exception.UndefinedEventException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

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
     * @see storage.Storage
     */
    public EventList(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * Adds an event to the list.
     *
     * @param eventToBeAdded may be Assignment/Class/Personal Event based on the usage
     */
    public void addEvent(Event eventToBeAdded) {
        events.add(eventToBeAdded);
    }

    /**
     * Mark a event as done.
     *
     * @param eventIndex the index of the event being done
     *                   //     * @throws UndefinedEventException the event is not defined but
     *                   // the user want to mark it as done
     */
    public void doneEvent(int eventIndex) throws UndefinedEventException {
        try {
            events.get(eventIndex).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new UndefinedEventException(eventIndex);
        }
    }

    /**
     * Edit the contents of an existing event.
     *
     * @param event the new edited event
     * @param index the index of the event being edited
     */
    public void editEvent(Event event, int index) {
        assert events != null;
        events.set(index, event);
    }

    /**
     * Sorts the events alphabetically.
     *
     * @param type the sorting criteria.
     */
    public void sortEvent(String type) {
        assert events != null;
        switch (type) {
        case "description":
            events.sort(Event.descriptionComparator);
            break;
        case "time":
            events.sort(Event.timeComparator);
            break;
        default:
            events.sort(Event.locationComparator);
            break;
        }

    }

    /**
     * Gives the access to the list of events.
     *
     * @return the list to be printed.
     */
    public ArrayList<Event> getEventList() {
        return events;
    }

    /**
     * Returns how many events are in the list.
     *
     * @return the size of the ArrayList in the EventList object.
     */
    public int getSize() {
        return events.size();
    }

    /**
     * Remove the event indicated by the user.
     */
    public void remove(int eventIndex) {
        assert events != null;
        events.remove(eventIndex);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param eventIndex index of the event to return.
     * @return the event at the specified position in this list.
     */
    public Event get(int eventIndex) {
        return events.get(eventIndex);
    }

    /**
     * Filter the event list to find the events contain the information looking for by the user.
     *
     * @param filterString the keyword that the user is looking for.
     * @return the filtered list. this list contains only the events that satisfy the requirement.
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
     * @param date the date that the user is looking for.
     * @return the filtered list. this list contains only the events that satisfy the requirement.
     */
    public ArrayList<Event> filterDateWith(LocalDate date) {
        ArrayList<Event> filteredEventList = (ArrayList<Event>) events.stream()
                .filter(s -> s.getDate().isEqual(date))
                .collect(toList());

        return filteredEventList;
    }

    /**
     * Clear the ArrayList events if it is not empty.
     *
     * @throws EmptyEventListException when the user tries to clear a list that is already empty.
     */
    public void clearEvents() throws EmptyEventListException {
        //assert events != null;
        if (events == null) {
            throw new EmptyEventListException();
        } else {
            events.clear();
        }
    }
}
