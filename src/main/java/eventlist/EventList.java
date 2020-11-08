package eventlist;


import event.Assignment;
import event.Class;
import event.Event;
import event.PersonalEvent;
import event.SelfStudy;
import exception.*;
import location.Location;
import location.OnlineLocation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;


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
        Event newEvent;
        String newDescription;
        Location newLocation = null;
        OnlineLocation newOnlineLocation = null;
        LocalDateTime start;
        LocalDateTime end = null;

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
            if (events.get(index) instanceof Assignment) {
                start = events.get(index).getEndDateTime();
            } else {
                start = events.get(index).getStartDateTime();
            }
        } else {
            start = startEnd[0];
        }

        if (startEnd[1] == null) {
            end = events.get(index).getEndDateTime();
            if (editInformation[4].equals("nil")) {
                end = null;
            }

            if (end == null && editInformation[0].equalsIgnoreCase("class")) {

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
            assert end != null;
            if (newLocation != null) {

                newEvent = new Class(newDescription, newLocation, start, end);
            } else {

                newEvent = new Class(newDescription, newOnlineLocation, start, end);
            }
            break;
        case "selfStudy":
            if (newLocation != null) {
                if (end == null) {
                    newEvent = new SelfStudy(newDescription, newLocation, start);
                } else {
                    newEvent = new SelfStudy(newDescription, newLocation, start, end);
                }
            } else {
                if (end == null) {
                    newEvent = new SelfStudy(newDescription, newOnlineLocation, start);
                } else {
                    newEvent = new SelfStudy(newDescription, newOnlineLocation, start, end);
                }
            }
            break;
        default:
            if (newLocation != null) {
                if (end == null) {
                    newEvent = new PersonalEvent(newDescription, newLocation, start);
                } else {
                    newEvent = new PersonalEvent(newDescription, newLocation, start, end);
                }
            } else {
                if (end == null) {
                    newEvent = new PersonalEvent(newDescription, newOnlineLocation, start);
                } else {
                    newEvent = new PersonalEvent(newDescription, newOnlineLocation, start, end);
                }
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
        // set new description
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
                (events.get(index)).setDateTime(startEnd[0]);
            }
        } else if (events.get(index) instanceof PersonalEvent) {
            if (startEnd[0] != null) {
                (events.get(index)).setDateTime(startEnd[0]);
            }
            if (startEnd[1] != null) {
                (events.get(index)).setEndDateTime(startEnd[1]);
            }
            if (editInformation[4].equalsIgnoreCase("nil")) {
                (events.get(index)).setEndDateTime(null);
            }
        } else if (events.get(index) instanceof Class) {
            if (startEnd[0] != null) {
                (events.get(index)).setDateTime(startEnd[0]);
            }
            if (startEnd[1] != null) {
                (events.get(index)).setEndDateTime(startEnd[1]);
            }
        } else if (events.get(index) instanceof SelfStudy) {
            if (startEnd[0] != null) {
                (events.get(index)).setDateTime(startEnd[0]);
            }
            if (startEnd[1] != null) {
                (events.get(index)).setEndDateTime(startEnd[1]);
            }
            if (editInformation[4].equalsIgnoreCase("nil")) {
                (events.get(index)).setEndDateTime(null);
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
        if (type.equalsIgnoreCase("description")) {
            events.sort(Event.descriptionComparator);
        } else if (type.equalsIgnoreCase("time")) {
            events.sort(Comparator.comparing(Event::getEndDateTime));
        } else {
            events.sort(Event.locationComparator);
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
                .filter(s -> (s.getDate().isEqual(date)
                        || (s.getDate().isBefore(date) && s.getEndDate().isAfter(date))))
                .collect(toList());

        return filteredEventList;
    }

    /**
     * Filter the event list to find the events happen before the date looking for by the user.
     *
     * @param date the date that the user is looking for.
     * @return the filtered list. this list contains only the events that satisfy the requirement.
     */
    public ArrayList<Event> filterDateBefore(LocalDate date) {
        ArrayList<Event> filteredEventList = (ArrayList<Event>) events.stream()
                .filter(s -> (s.getDate().compareTo(date) < 0))
                .collect(toList());

        return filteredEventList;
    }

    /**
     * Filter the event list to find the academic related events happen on the date that have been done already.
     *
     * @param date the date that the user is looking for.
     * @return he filtered list. this list contains only the events that satisfy the requirement.
     */
    public ArrayList<Event> filterDateDoneAcademicEventWith(LocalDate date) {
        ArrayList<Event> filteredEventList = filterDateWith(date);
        filteredEventList = (ArrayList<Event>) filteredEventList.stream()
                .filter(s -> (s.isDone() && ((s instanceof Class) || (s instanceof SelfStudy))))
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

    /**
     * Filter the event list to find the events happen on the date that have not been done yet.
     *
     * @param date the date that the user is looking for.
     * @return he filtered list. this list contains only the events that satisfy the requirement.
     */
    public ArrayList<Event> filterDateNotDoneWith(LocalDate date) {
        ArrayList<Event> filteredEventList = filterDateWith(date);
        filteredEventList = (ArrayList<Event>) filteredEventList.stream()
                .filter(s -> ((!s.isDone()) && (s.getEndDate().isEqual(date))))
                .collect(toList());
        return filteredEventList;
    }

    /**
     * Add all the classes happening in the current week for several weeks into the list.
     *
     * @param numWeeks number of weeks to repeat.
     */
    public void repeatAllClasses(int numWeeks) throws NoClassWeekException {
        ArrayList<Event> filteredClassList;
        //the filtered list are all the classes happening in the current week
        try {
            filteredClassList = (ArrayList<Event>) events.stream()
                    .filter(s -> ((isLocalDateInTheSameWeek(s.getDate(), LocalDate.now()))
                            && s instanceof Class))
                    .collect(toList());
        } catch (NullPointerException e) {
            throw new NoClassWeekException();
        }
        if (filteredClassList.size() == 0) {
            throw new NoClassWeekException();
        }

        ArrayList<Event> copyList = new ArrayList<>();

        for (int i = 1; i <= numWeeks; i++) {
            filteredClassList.forEach(s -> copyList.add(s.clone()));
            int finalI = i;
            copyList.forEach(s -> s.setDateTime(s.getStartDateTime().plusWeeks(finalI)));
            copyList.forEach(s -> s.setEndDateTime(s.getEndDateTime().plusWeeks(finalI)));
            events.addAll(copyList);
            copyList.clear();
        }
    }

    //@@author Sunil Katti-reused
    //Reused from https://stackoverflow.com/a/56246095 with minor modifications

    /**
     * Check whether 2 dates are of the same week.
     *
     * @param date1 the first date
     * @param date2 the second date
     * @return ture if they are of the same week. false otherwise.
     */
    public static boolean isLocalDateInTheSameWeek(LocalDate date1, LocalDate date2) {
        assert date1 != null;
        assert date2 != null;
        LocalDate mondayBeforeDate1 = date1.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate sundayAfterDate1 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return ((date2.isEqual(mondayBeforeDate1) || date2.isAfter(mondayBeforeDate1))
                && (date2.isEqual(sundayAfterDate1) || date2.isBefore(sundayAfterDate1)));
    }
    //@@author

    /**
     * Add the selected event for several weeks into the list.
     *
     * @param numWeeks number of weeks to repeat.
     */
    public void repeatEvent(int eventIndex, int numWeeks) {
        Event newEvent;
        for (int i = 1; i <= numWeeks; i++) {
            newEvent = events.get(eventIndex).clone();
            newEvent.setDateTime(newEvent.getStartDateTime().plusWeeks(i));
            newEvent.setEndDateTime(newEvent.getEndDateTime().plusWeeks(i));
            events.add(newEvent);
        }
    }

    /**
     * Clear all events before a certain date.
     *
     * @param clearDate before which all events to be cleared
     */
    public void clearBefore(LocalDate clearDate) throws EmptyEventListException {
        if (events != null) {
            events.removeIf(event -> event.getDate().compareTo(clearDate) < 0);
        } else {
            throw new EmptyEventListException();
        }
    }
}
