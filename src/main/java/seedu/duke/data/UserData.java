package seedu.duke.data;

import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Goal;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidListException;

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

    public void addToEventList(String name, Event e) throws InvalidListException {
        assert e != null : "event cannot be null";
        getEventList(name).add(e);
    }

    public EventList getEventList(String name) throws InvalidListException {
        assert name != null : "name should not be null";
        for (EventList e : eventLists) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        throw new InvalidListException(name + " list does not exist.");
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
