package seedu.duke.data;

import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Goal;
import seedu.duke.event.Personal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class UserData {
    private ArrayList<EventList> eventLists = new ArrayList<>();
    private Goal goal;

    public UserData() {
        eventLists.add(new EventList("Personal"));
        eventLists.add(new EventList("Timetable"));
        eventLists.add(new EventList("Zoom"));
        setGoal(null);
    }

    public UserData(ArrayList<EventList> eventLists) {
        this.eventLists = new ArrayList<>(eventLists);
    }

    public void addToEventList(String name, Event e) {
        getEventList(name).add(e);
    }

    public EventList getEventList(String name) {
        for (EventList e : eventLists) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null; //change this to throw exception, list not found.
    }

    public ArrayList<EventList> getAllEventLists() {
        return eventLists;
    }

    /**
     * Sets the user's goal.
     *
     * @param goal to set to.
     */
    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    /**
     * Returns the user's goal.
     *
     * @return user's goal.
     */
    public Goal getGoal() {
        return goal;
    }

    /**
     * Set the user's deadline for personal events with date only.
     *
     * @param index of event
     * @param date  of event to be completed
     */
    public Event deadlineChangeDateOnly(int index, LocalDate date) {
        EventList personalEvent = getEventList("Personal");
        Event oldEvent = personalEvent.getEventByIndex(index - 1);
        Event updateEvent = null;
        if (oldEvent != null) {
            updateEvent = new Personal(oldEvent.getDescription(), date);
            getEventList("Personal").setEvent(index - 1, updateEvent);
        }
        return updateEvent;
    }

    /**
     * Set the user's deadline for personal events with date and time.
     *
     * @param index of event
     * @param date  of event to be completed
     * @param time  of event to be completed
     */
    public Event deadlineChangeDateTime(int index, LocalDate date, LocalTime time) {
        EventList personalEvent = getEventList("Personal");
        Event oldEvent = personalEvent.getEventByIndex(index - 1);
        Event updateEvent = null;
        if (oldEvent != null) {
            updateEvent = new Personal(oldEvent.getDescription(), date, time);
            getEventList("Personal").setEvent(index - 1, updateEvent);
        }
        return updateEvent;
    }
}
