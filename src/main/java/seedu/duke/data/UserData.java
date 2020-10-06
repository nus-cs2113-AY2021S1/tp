package seedu.duke.data;

import seedu.duke.event.Event;
import seedu.duke.event.EventList;

import java.util.ArrayList;

public class UserData {
    private ArrayList<EventList> eventLists = new ArrayList<>();

    public UserData() {
        eventLists.add(new EventList("Personal"));
        eventLists.add(new EventList("Timetable"));
        eventLists.add(new EventList("Zoom"));
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
}
