package seedu.duke.data;

import seedu.duke.EventLogger;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Goal;
import seedu.duke.exception.InvalidListException;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Stores the data of the user.
 */
public class UserData {
    private ArrayList<EventList> eventLists = new ArrayList<>();
    private Goal goal;
    private static Logger logger = EventLogger.getEventLogger();

    /**
     * Constructor for UserData. Initialises with empty data.
     */
    public UserData() {
        eventLists.add(new EventList("Personal"));
        eventLists.add(new EventList("Timetable"));
        eventLists.add(new EventList("Zoom"));
        setGoal(null);
    }

    /**
     * Constructor for UserData. Initialises the data with the given eventLists.
     *
     * @param eventLists to initialise UserData with.
     */
    public UserData(ArrayList<EventList> eventLists) {
        this.eventLists = new ArrayList<>(eventLists);
    }

    /**
     * Adds the given Event into the EventList with specified name.
     *
     * @param name of EventList to add to.
     * @param e    Event to add to EventList.
     * @throws InvalidListException if a list with the given name does not exist.
     */
    public void addToEventList(String name, Event e) throws InvalidListException {
        assert e != null : "event cannot be null";
        getEventList(name).add(e);
    }

    /**
     * Returns the EventList with the given name.
     *
     * @param name of EventList to retrieve.
     * @return EventList with the given name.
     * @throws InvalidListException if a list with the given name does not exist.
     */
    public EventList getEventList(String name) throws InvalidListException {
        assert name != null : "name should not be null";
        for (EventList e : eventLists) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        logger.warning("InvalidListException encountered -- " + name + " list does not exist");
        throw new InvalidListException(name + " list does not exist.");
    }

    /**
     * Returns the ArrayList of EventList that contains data of all events.
     *
     * @return ArrayList of EventList.
     */
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
