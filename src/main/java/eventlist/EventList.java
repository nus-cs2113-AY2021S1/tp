package eventlist;


import event.Assignment;
import event.Class;
import event.Event;
import event.PersonalEvent;
import exception.EditNoEndTimeException;
import exception.EmptyEventListException;
import exception.ExistingEventInListException;
import exception.EndBeforeStartEventException;
import exception.UndefinedEventException;
import location.Location;
import location.OnlineLocation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;


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
    public void addEvent(Event eventToBeAdded) throws ExistingEventInListException {
        if (events.contains(eventToBeAdded)) {
            throw new ExistingEventInListException();
        }

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
     * @param index           the index of the event being edited.
     * @param editInformation array containing command type and description.
     * @param startEnd        array containing start date and end date.
     * @param location        new location.
     * @param onlineLocation  new online location.
     * @return Event new edited events
     */
    public Event editEvent(int index, String[] editInformation, LocalDateTime[] startEnd, Location location,
                           OnlineLocation onlineLocation) throws EndBeforeStartEventException, EditNoEndTimeException {
        assert events != null;


        // no change in event type
        if (editInformation[0].isBlank()) {
            editSameType(index, editInformation, startEnd, location, onlineLocation);
        } else {
            // create new event object with user input
            editNewType(index, editInformation, startEnd, location, onlineLocation);
        }

        return events.get(index);
    }

    /**
     * Edit the contents of an existing event. Will only be called when the event type is changed.
     *
     * @param index           the index of the event being edited.
     * @param editInformation array containing command type and description.
     * @param startEnd        array containing start date and end date.
     * @param location        new location.
     * @param onlineLocation  new online location.
     */
    private void editNewType(int index, String[] editInformation, LocalDateTime[] startEnd, Location location,
                             OnlineLocation onlineLocation) throws EditNoEndTimeException,
            EndBeforeStartEventException {
        Event newEvent = null;
        String newDescription;
        Location newLocation = null;
        OnlineLocation newOnlineLocation = null;
        LocalDateTime start;
        LocalDateTime end;

        // newDescription is set to the user input if it is not null. Otherwise, set it to the original value.
        if (editInformation[1].isBlank()) {
            newDescription = events.get(index).getDescription();
        } else {
            newDescription = editInformation[1];
        }

        // newLocation is set to the user input if it is not null. Otherwise, set it to the original value.
        if (location == null && onlineLocation == null) {
            if (events.get(index).getLocation() != null) {
                newLocation = events.get(index).getLocation();
            }
            if (events.get(index).getLink() != null) {
                newOnlineLocation = events.get(index).getLink();
            }
        } else if (location != null && onlineLocation == null) {
            newLocation = location;
            newOnlineLocation = null;
        } else {
            newLocation = null;
            newOnlineLocation = onlineLocation;
        }

        // start and end are set to the user input if they are not null. Otherwise, set them to their original value.
        if (startEnd[0] == null) {
            if (events.get(index) instanceof  Assignment) {
                start = events.get(index).getEndDateTime();
            } else {
                start = events.get(index).getStartDateTime();
            }
        } else {
            start = startEnd[0];
        }

        if (startEnd[1] == null) {
            end = events.get(index).getEndDateTime();
            // conversion from an assignment to other type would result in an error if END date is not specified.
            if (end == null && !editInformation[0].equals("assignment")) {
                throw new EditNoEndTimeException();
            }
        } else {
            end = startEnd[1];
        }

        // create new event object using the new fields.
        switch (editInformation[0]) {
        case "assignment":
            if (newLocation != null) {
                newEvent = new Assignment(newDescription, newLocation, start);
            } else {
                newEvent = new Assignment(newDescription, newOnlineLocation, start);
            }
            break;
        case "class":
            if (newLocation != null) {
                newEvent = new Class(newDescription, newLocation, start, end);
            } else {
                newEvent = new Class(newDescription, newOnlineLocation, start, end);
            }
            break;
        default:
            if (newLocation != null) {
                newEvent = new PersonalEvent(newDescription, newLocation, start, end);
            } else {
                newEvent = new PersonalEvent(newDescription, newOnlineLocation, start, end);
            }
            break;
        }
        events.set(index, newEvent);
    }

    /**
     * Edit the contents of an existing event. Will only be called when the event type remains the same.
     *
     * @param index           the index of the event being edited.
     * @param editInformation array containing command type and description.
     * @param startEnd        array containing start date and end date.
     * @param location        new location.
     * @param onlineLocation  new online location.
     */
    private void editSameType(int index, String[] editInformation, LocalDateTime[] startEnd, Location location,
                              OnlineLocation onlineLocation) {
        if (!editInformation[1].isBlank()) {
            events.get(index).setDescription(editInformation[1]);
        }

        // set new location
        if (location != null) {
            events.get(index).setLocation(location);
            events.get(index).setLink(null);
        }
        if (onlineLocation != null) {
            events.get(index).setLink(onlineLocation);
            events.get(index).setLocation(null);
        }

        // set new time
        if (events.get(index) instanceof Assignment) {
            if (startEnd[0] != null) {
                ((Assignment) events.get(index)).setBy(startEnd[0]);
            }
        } else if (events.get(index) instanceof PersonalEvent) {
            if (startEnd[0] != null) {
                ((PersonalEvent) events.get(index)).setAt(startEnd[0]);
            }
            if (startEnd[1] != null) {
                ((PersonalEvent) events.get(index)).setEnd(startEnd[1]);
            }
        } else if (events.get(index) instanceof Class) {
            if (startEnd[0] != null) {
                ((Class) events.get(index)).setAt(startEnd[0]);
            }
            if (startEnd[0] != null) {
                ((Class) events.get(index)).setEnd(startEnd[1]);
            }
        }
    }

    /**
     * Sorts the events alphabetically.
     *
     * @param type the sorting criteria.
     */
    public void sortEvent(String type) {
        assert events != null;
        if (type.equals("description")) {
            events.sort(Event.descriptionComparator);
        } else if (type.equals("time")) {
            events.sort(Comparator.comparing(Event::getEndDateTime));
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

    /**
     * Checks whether there is any conflicting events in terms of timing.
     *
     * @param event the new added event.
     * @return the filtered event arraylist. If there is no conflict, return null.
     */
    public ArrayList<Event> checkConflictTiming(Event event) {
        LocalDateTime eventStartDateTime = event.getStartDateTime();
        LocalDateTime eventEndDateTime;
        try {
            eventEndDateTime = event.getEndDateTime();
        } catch (NullPointerException e) {
            eventEndDateTime = null;
        }
        ArrayList<Event> filteredEventList;
        ArrayList<Event> filteredEventList2 = null;
        try {
            filteredEventList = (ArrayList<Event>) events.stream()
                    .filter(s -> s.getEndDateTime() != null)
                    .filter(s -> ((!(s instanceof Assignment))
                            && (s.getStartDateTime().isBefore(eventStartDateTime)
                            || s.getStartDateTime().isEqual(eventStartDateTime))
                            && (s.getEndDateTime().isAfter(eventStartDateTime)
                            || s.getEndDateTime().isEqual(eventStartDateTime))))
                    .collect(toList());
        } catch (NullPointerException e) {
            filteredEventList = null;
        }
        if (eventEndDateTime != null) {
            //this considers when the events already in the list lie in the duration of the new event
            try {
                LocalDateTime finalEventEndDateTime = eventEndDateTime;
                filteredEventList2 = (ArrayList<Event>) events.stream()
                        .filter(s -> ((!(s instanceof Assignment))
                                && (s.getStartDateTime().isAfter(eventStartDateTime)
                                || s.getStartDateTime().isEqual(eventStartDateTime))
                                && (s.getStartDateTime().isBefore(finalEventEndDateTime)
                                || s.getStartDateTime().isEqual(finalEventEndDateTime))))
                        .collect(toList());
                filteredEventList2.removeAll(filteredEventList);
            } catch (NullPointerException e) {
                filteredEventList2 = null;
            }
        }
        if (filteredEventList != null && filteredEventList2 != null) {
            filteredEventList.addAll(filteredEventList2);
            filteredEventList.remove(event);
        }
        return filteredEventList;
    }
}
