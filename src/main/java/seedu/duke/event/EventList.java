
package seedu.duke.event;

import seedu.duke.hr.Member;
import seedu.duke.hr.MemberList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EventList {
    public static ArrayList<Event> events = new ArrayList<>();
    public static Logger logger = Logger.getGlobal();
    public static Scanner scanner = new Scanner(System.in);

    public static final String COMMAND_EVENT_NOT_EXIST =
            "OOPS!!! The event does not exist.Please try our help command!\n";
    public static final String ARE_YOU_SURE_THAT_YOU_WANT_TO_CLEAR_THE_LIST =
            "Are you sure you want to clear the list? Y/N\n";
    public static final String COMMAND_EVENT_LIST_CLEAR_SUCCESSFUL = "The list has been cleared!";
    public static final String COMMAND_EVENT_LIST_CLEAR_UNSUCCESSFUL = "The list will not be cleared!";
    public static final String COMMAND_EVENT_LIST_EMPTY = "Oops! The event list is empty!";
    public static final String COMMAND_ADD_EVENT_SUCCESSFUL = "Got it. I've added this Event:\n";
    public static final String COMMAND_DELETE_EVENT_SUCCESSFUL = "Got it! I'll remove this Event:\n";
    public static final String COMMAND_MARK_EVENT_AS_COMPLETE_SUCCESSFUL = "Nice! I've marked this task as done:\n";
    public static final String COMMAND_KEYWORD_NOT_FOUND = "No matching events found!";
    public static final String COMMAND_NOT_VALID_DATE =
            "Oops! Seems like you have entered a past date. Please enter a valid date.";

    public static Event getEvent(int index) {
        return events.get(index);
    }

    /**
     * Adds member to the arraylist.
     *
     * @param event event to be added in the list.
     */
    public static String addEvent(Event event) {
        logger.setLevel(Level.OFF);
        logger.info("Adding event to list\n");
        String userOutput;

        LocalDate date = event.getEventDate();
        if (date.isBefore(LocalDate.now())) {
            return COMMAND_NOT_VALID_DATE;
        } else {
            events.add(event);
            userOutput = COMMAND_ADD_EVENT_SUCCESSFUL + event.printEvent()
                    + "\nNow you have " + events.size() + " event in the list.\n";
            logger.info("Added event to list\n");
        }

        return userOutput;
    }

    /**
     * To delete an event based on the given index.
     *
     * @param index index to be deleted from list
     */
    public static String deleteEvent(int index) {
        logger.setLevel(Level.OFF);
        logger.info("Deleting event\n");
        String userOutput;
        try {
            userOutput = COMMAND_DELETE_EVENT_SUCCESSFUL;
            userOutput = userOutput.concat(events.get(index).printEvent() + "\n");
            events.remove(index);
            userOutput = userOutput.concat("Now you have " + events.size() + " event in the list.");
            logger.info("Deleted test from list\n");
        } catch (IndexOutOfBoundsException e) {
            userOutput = COMMAND_EVENT_NOT_EXIST;
        }
        return userOutput;
    }

    /**
     * Deletes all the events in the list.
     * @return output String informing if list is emptied
     */
    public static String clearEvents() {
        logger.setLevel(Level.OFF);
        logger.info("Clearing event\n");
        String output = "";
        if (events.size() == 0) {
            logger.warning("Empty event list.\n");
            output = COMMAND_EVENT_LIST_EMPTY;
        } else {
            System.out.println(ARE_YOU_SURE_THAT_YOU_WANT_TO_CLEAR_THE_LIST);
            String userInput = scanner.nextLine();
            if (userInput.toLowerCase().equals("y")) {
                events.clear();
                output = output.concat(COMMAND_EVENT_LIST_CLEAR_SUCCESSFUL);
            } else {
                output = output.concat(COMMAND_EVENT_LIST_CLEAR_UNSUCCESSFUL);
            }
        }
        return output;
    }

    /**
     * Marks a event as done.
     *
     * @param index item to be marked as completed
     * @return userOutput to be printed out
     */
    public static String isCompleted(int index) {
        String userOutput = "";
        try {
            int numToBeMarked = index + 1;
            Event event = events.get(index);
            event.isDone = true;

            userOutput = userOutput.concat(COMMAND_MARK_EVENT_AS_COMPLETE_SUCCESSFUL
                    + numToBeMarked + "." + events.get(index).printEvent());
        } catch (IndexOutOfBoundsException e) {
            userOutput = COMMAND_EVENT_NOT_EXIST;
        }
        return userOutput;
    }


    /**
     * Prints a list of events.
     *
     * @return list of events
     */
    public static String printEventList() {
        logger.setLevel(Level.OFF);
        logger.info("Initialising event list\n");
        String userOutput;
        if (events.size() == 0) {
            logger.warning("Empty event list.\n");
            userOutput = (COMMAND_EVENT_LIST_EMPTY);
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
            userOutput = COMMAND_EVENT_LIST_EMPTY;
        }
        return userOutput;
    }

    /**
     * Displays the list of tasks containing the keyword.
     *
     * @param keyword The keyword to be searched for.
     */

    public static String searchEvents(String keyword) {
        String output = "";
        try {
            if (checkIfEventNameMatch(keyword)) {
                output = output.concat(printFilteredEventsByName(keyword));
            } else if (checkIfEventDateMatch(keyword)) {
                output = output.concat(printFilteredEventsByDate(keyword));
            } else {
                output = output.concat(COMMAND_KEYWORD_NOT_FOUND);
            }
        } catch (Exception e) {
            output = output.concat(COMMAND_KEYWORD_NOT_FOUND);
        }
        return output;
    }


    /**
     * Used to check if there is at least one task containing the keyword in it's description.
     *
     * @param keyword The word used for search.
     * @return returns true if at least one event contains the event name.
     */
    public static boolean checkIfEventNameMatch(String keyword) {
        boolean hasMatchedTask = false;
        for (Event event : events) {
            if (event.containsNameKeyword(keyword)) {
                hasMatchedTask = true;
                break;
            }
        }
        return hasMatchedTask;
    }

    /**
     * Used to check if there is at least one task containing the keyword in it's description.
     *
     * @param keyword The word used for search.
     * @return returns true if at least one event contains the event date
     */
    public static boolean checkIfEventDateMatch(String keyword) {
        assert keyword != null;
        LocalDate date = LocalDate.parse(keyword);
        boolean hasMatchedTask = false;
        for (Event event : events) {
            if (event.containsDateKeyword(date)) {
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
    private static String printFilteredEventsByName(String keyword) {
        assert keyword != null;
        int taskNumber = 1;
        String output = "";
        for (Event event : events) {
            if ((event.containsNameKeyword(keyword))) {
                output = output + taskNumber + "." + event.printEvent() + "\n" + "*".repeat(50) + "\n";
                taskNumber++;
            }
        }
        return output;
    }

    private static String printFilteredEventsByDate(String keyword) {
        assert keyword != null;
        int taskNumber = 1;
        String output = "";
        for (Event event : events) {
            if ((event.containsDateKeyword(LocalDate.parse(keyword)))) {
                output = output + taskNumber + "." + event.printEvent() + "\n" + "*".repeat(50) + "\n";
                taskNumber++;
            }
        }
        return output;
    }

    public static Event findEventByName(String eventName) {
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(eventName)) {
                return event;
            }
        }
        return null;
    }

    public static boolean checkEventExistence(String memberName) {
        boolean hasExist = false;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventName().equalsIgnoreCase(memberName)) {
                hasExist = true;
            }
        }
        return hasExist;
    }

    /*
    public static String addAttendance(String eventName, String memberName) {
        String output = "";
        if (checkEventExistence(eventName)) {
            Event e = findEventByName(eventName);
            if (MemberList.checkMemberExistence(MemberList.members, memberName)) {
                if (!MemberList.checkMemberExistence(e.getEventParticipants(), memberName)) {
                    Member m = MemberList.findMemberByName(memberName);
                    e.setEventParticipants(m);
                    output = "Noted. I have added this participant to this event:\n";
                    output = output.concat(m.getMemberName() + "\n");
                    output = output.concat("Now you have " + e.eventParticipants.size() + " member"
                            + ((e.eventParticipants.size() == 1) ? "" : "s") + " participated in "
                            + e.eventName + ".\n");
                } else {
                    output = output.concat("Member attendance had already been taken!\n");
                }
            } else {
                output = output.concat("Member does not exist!\n");
            }
        } else {
            output = output.concat("Event does not exist!\n");
        }
        return output;
    }*/

    public static String addAttendance(String eventName, String memberName) {
        String output = "";
        if (!checkEventExistence(eventName)) {
            output = output.concat("Event does not exist!\n");
            return output;
        }
        if (!MemberList.checkMemberExistence(MemberList.members, memberName)) {
            output = output.concat("Member does not exist!\n");
            return output;
        }
        Event e = findEventByName(eventName);
        if (MemberList.checkMemberExistence(e.getEventParticipants(), memberName)) {
            output = output.concat("Member attendance had already been taken!\n");
            return output;
        }
        Member m = MemberList.findMemberByName(memberName);
        e.setEventParticipants(m);
        output = "Noted. I have added this participant to this event:\n";
        output = output.concat(m.getMemberName() + "\n");
        output = output.concat("Now you have " + e.eventParticipants.size() + " member"
                + ((e.eventParticipants.size() == 1) ? "" : "s") + " participated in "
                + e.eventName + ".\n");
        return output;
    }


    public static String listAttendance(String eventName) {
        String output = "";
        if (checkEventExistence(eventName)) {
            Event e = findEventByName(eventName);
            output = "The following " + ((e.getEventParticipants().size() > 1) ? "members have" : "member has")
                    + " participated in this event:\n";
            for (int i = 0; i < e.getEventParticipants().size(); i++) {
                int index = i + 1;
                output = output.concat(index + ". " + e.getEventParticipants().get(i).getMemberName() + "\n");
            }
            output = output.concat("Now you have " + e.eventParticipants.size() + " member"
                    + ((e.eventParticipants.size() == 1) ? "" : "s") + " attended "
                    + e.eventName + ".\n");
        } else {
            output = output.concat("Event does not exist!\n");
        }
        return output;
    }

    public static String deleteAttendance(String n, String m) {
        String output = "";

        return output;
    }
}


