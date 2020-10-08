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

}
