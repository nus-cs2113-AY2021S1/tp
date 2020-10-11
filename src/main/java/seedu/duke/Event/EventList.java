package seedu.duke.event;


import java.util.ArrayList;

public class EventList {
    public static ArrayList<Event> events = new ArrayList<>();

    /**
     * Adds member to the arraylist.
     * @param event event to be added in the list.
     */
    public static String addEvent(Event event) {
        String userOutput;
        events.add(event);
        userOutput = "Got it. I've added this Event:\n" + event.printEvent() + "\n"
                + "Now you have " + events.size() + " events in the list.\n";

        return userOutput;
    }

    /**
     * To delete an event based on the given index.
     * @param index index to be deleted from list
     */
    public static String deleteEvent(int index) {
        String userOutput;
        try {
            userOutput = "I'll remove this Event:\n";
            userOutput = userOutput.concat(events.get(index).printEvent() + "\n");
            events.remove(index);
            userOutput = userOutput.concat("Now you have " + events.size() + " event in the list");
        } catch (IndexOutOfBoundsException e) {
            userOutput = "OOPS!!! The event does not exist.Please try our help command!\n";
        }
        return userOutput;
    }

    public static String printEventList() {
        String userOutput;
        if (events.size() == 0) {
            userOutput = ("Oops! The event list is empty!");
        } else {
            userOutput = "Here are the current events in your list:\n";

            for (Event event : events) {
                userOutput = userOutput.concat(events.indexOf(event) + 1 + ".");
                userOutput = userOutput.concat(event.printEvent());
            }
        }
        return userOutput;
    }


}


