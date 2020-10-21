
package seedu.duke.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;


public class EventList {
    public static ArrayList<Event> events = new ArrayList<>();
    public static Logger logger = Logger.getGlobal();

    public static Event getEvent(int index) {
        return events.get(index);
    }

    /**
     * Adds member to the arraylist.
     *
     * @param event event to be added in the list.
     */
    public static String addEvent(Event event) {
        logger.info("Adding event to list\n");
        String userOutput;
        events.add(event);
        userOutput = "Got it. I've added this Event:\n" + event.printEvent()
                + "\nNow you have " + events.size() + " event in the list.\n";
        logger.info("Added event to list\n");
        return userOutput;
    }

    /**
     * To delete an event based on the given index.
     *
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

    /**
     * Marks a event as done.
     *
     * @param index item to be marked as completed
     * @return userOutput to be printed out
     */
    public static String isCompleted(int index) {
        String userOutput;
        int numToBeMarked = index + 1;
        Event event = events.get(index);
        event.isDone = true;
        userOutput = "Nice! I've marked this task as done:\n"
                + numToBeMarked + "." + events.get(index).printEvent();

        return userOutput;
    }


    /**
     * Prints a list of events.
     *
     * @return list of events
     */
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
                userOutput = userOutput.concat(event.printEvent()) + "\n" + "*".repeat(50) + "\n";
            }
            logger.info("Event List ready");
        }

        return userOutput;
    }

    /**
     * Shows number of left to events.
     *
     * @return number of days left to event.
     */
    public static String countdownView() {
        String userOutput = "";
        int eventNumber = 1;
        ArrayList<Event> eventsSortedByDate = events;
        eventsSortedByDate.sort(Comparator.comparing(Event::getEventDate));
        if (events.size() != 0) {
            for (Event event : eventsSortedByDate) {
                if (!event.getEventDate().isBefore(LocalDate.now())) {
                    userOutput = userOutput + eventNumber + "." + event.printEvent();
                    userOutput = userOutput.concat("\nNumber of day(s) left: " + event.numberOfDaysLeft())
                            + "\n" + "*".repeat(50) + "\n";
                    eventNumber++;
                }
            }
        } else {
            userOutput = "The list is empty";
        }
        return userOutput;
    }

    /**
     * Displays the list of tasks containing the keyword.
     *
     * @param keyword The keyword to be searched for.
     */
    public static String searchEvents(String keyword) {
        String output;
        boolean containsEvents = checkIfEventsMatch(keyword);
        if (containsEvents) {
            output = printFilteredEvents(keyword);
        } else {
            output = "Empty event list";
        }
        return output;
    }


    /**
     * Used to check if there is at least one task containing the keyword in it's description.
     *
     * @param keyword The word used for search.
     * @return returns true if at least one event contains the event name.
     */
    public static boolean checkIfEventsMatch(String keyword) {
        boolean hasMatchedTask = false;
        for (Event event : events) {
            if (event.containsKeyword(keyword)) {
                hasMatchedTask = true;
                break;
            }
        }
        return hasMatchedTask;
    }

    /**
     * Displays the list of tasks containing the keyword.
     *
     * @param keyword The word used for search.
     */
    private static String printFilteredEvents(String keyword) {
        int taskNumber = 1;
        String output = "";
        for (Event event : events) {
            if (event.containsKeyword(keyword)) {
                output = output + taskNumber + "." + event.printEvent() + "\n" + "*".repeat(50) + "\n";
                taskNumber++;
            }
        }
        return output;
    }

}


