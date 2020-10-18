
package seedu.duke.event;


import java.util.ArrayList;
import java.util.logging.Logger;

public class EventList {
    public static ArrayList<Event> events = new ArrayList<>();
    public static Logger logger = Logger.getGlobal();

    /**
     * Adds member to the arraylist.
     * @param event event to be added in the list.
     */
    public static String addEvent(Event event) {
        logger.info("Adding event to list\n");
        String userOutput;
        events.add(event);
        userOutput = "Got it. I've added this Event:\n" + event.printEvent() + "\n"
                + "Now you have " + events.size() + " event in the list.\n";
        logger.info("Added event to list\n");
        return userOutput;
    }

    /**
     * To delete an event based on the given index.
     * @param index index to be deleted from list
     */
    public static String deleteEvent(int index) {
        logger.info("Deleting event\n");
        String userOutput;
        try {
            userOutput = "I'll remove this Event:\n";
            userOutput = userOutput.concat(events.get(index).printEvent() + "\n");
            events.remove(index);
            userOutput = userOutput.concat("Now you have " + events.size() + " event in the list.");
            logger.info("Deleted test from list\n");
        } catch (IndexOutOfBoundsException e) {
            userOutput = "OOPS!!! The event does not exist.Please try our help command!\n";
        }
        return userOutput;
    }

    public static String isCompleted(int index) {
        String userOutput;
        int numToBeMarked = index + 1;
        Event event = events.get(index);
        event.isDone = true;
        userOutput =  "Nice! I've marked this task as done:\n"
                + numToBeMarked +"." + events.get(index).printEvent();

        return userOutput;
    }

    public static String printEventList() {
        logger.info("Initialising event list\n");
        String userOutput;
        if (events.size() == 0) {
            logger.warning("Empty event list.\n");
            userOutput = ("Oops! The event list is empty!");
        } else {
            userOutput = "Here are the current events in your list:\n";

            for (Event event : events) {
                userOutput = userOutput.concat(events.indexOf(event) + 1 + ".");
                userOutput = userOutput.concat(event.printEvent());
            }
            logger.info("Event List ready");
        }

        return userOutput;
    }


}

