package ui;


import event.Event;
import exception.EmptyEventListException;
import location.BusStop;
import location.Location;
import locationlist.LocationList;
import usercommunication.UserInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI receives all user inputs, and produces outputs show to the user.
 */
public class UI {

    public static final String LOGO =
            " _       _ _        _   ____       _                    _        _\n"
                    + "| |     | | |      | | / ___|     | |                  | |      | |\n"
                    + "|  \\    | | |      | |/ /         | |                  | |      | |\n"
                    + "| |\\\\   | | |      | |\\ \\         | |        ___       | |      | | ___\n"
                    + "| | \\\\  | | |      | | \\ \\     ___| |______ /___\\  ____| |_    _| |/___\\\n"
                    + "| |  \\\\ | | |      | |  \\ \\   /___|  ____  ||___|||  __  | |  | | ||___||\n"
                    + "| |   \\\\| | |      | |   \\ \\ //   | |    | | ___/ | |  | | |  | | | ___/\n"
                    + "| |    \\  | |______| |___/ / \\\\___| |    | |\\____ | |__| | |__| | |\\____\n"
                    + "|_|     |_|__________|____/   \\___|_|    |_|\\___/ |______|\\___,_|_|\\___/\n";
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final Scanner in;

    public UI() {
        in = new Scanner(System.in);
    }

    public String[] readEditCommand() {
        String[] editFields = {"TYPE: ", "DESC: ", "LOCATION: ", "START: ", "END: "};
        String[] editInformation = new String[5];
        System.out.println("Enter the edits for each component. Leave as blank if no edits are to be made.");
        // populate editinformation with user data
        for (int i = 0; i < 5; i++) {
            System.out.print(editFields[i]);
            editInformation[i] = in.nextLine().trim();
        }
        return editInformation;
    }


    /**
     * Reads the user input line by line.
     *
     * @return the string of the line
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the logo of DUKE and greet the user.
     */
    public void printGreetingMessage(UserInfo userInfo) {
        printLine();
        try {
            helloWithName(userInfo.getName());
        } catch (NullPointerException e) {
            System.out.println("I am NUSchedule! What's your name?");
        }
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the separator.
     */
    public void printLine() {
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Prints all the events with labels, based on the input list.
     *
     * @param events an ArrayList of events to be printed one by one.
     */
    public void printEventList(ArrayList<Event> events) throws EmptyEventListException {
        int numPrintedEvents = 0;
        if (events.size() == 0) {
            throw new EmptyEventListException();
        } else {
            try {
                System.out.println("Here are the Events in your list:");
                for (Event event : events) {
                    numPrintedEvents++;
                    System.out.println(numPrintedEvents + ". " + event.toString());
                }
            } catch (NullPointerException e) {
                System.out.println("The list is empty.");
            }
        }
    }

    /**
     * Prints all the Events of the filtered list with labels, based on the input list.
     *
     * @param events an ArrayList of Events to be printed one by one.
     */
    public void printFilteredEventList(ArrayList<Event> events) {
        int numPrintedEvents = 0;

        System.out.println("Here are the matching Events in your list:");
        for (Event event : events) {
            numPrintedEvents++;
            System.out.println(numPrintedEvents + ". " + event.toString());
        }
    }

    /**
     * Shows the error message when experiencing exceptions.
     *
     * @param message the message get from the error.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows the error message during loading. Since the general IO exception is
     * handled when executing the process, the error leads to this would be the file
     * is edited in a wrong way.
     */
    public void showLoadingError() {
        System.out.println("You edit the file in a wrong format. Please check.");
    }

    /**
     * Prints the message during executing commands.
     * This function is used to make all printing being done in UI.
     *
     * @param message determined by the command
     */
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Call when exit the program.
     */
    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Take in a size and prints the total number of Events.
     *
     * @param size number of events in the list.
     */
    public void printNumEvent(int size) {
        System.out.println("Now you have " + size + " Events in the list.");
    }

    /**
     * Prints the message when the user mark some Event as done.
     *
     * @param event the Event being done
     */
    public void printDoneMessage(Event event) {
        System.out.println("Nice! I've marked this Event as done: ");
        System.out.println(event.toString());
    }

    /**
     * Prints the message when the user deletes some Event.
     *
     * @param event the Event being deleted
     */
    public void printDeleteMessage(Event event) {
        System.out.println("Noted. I've removed this Event: ");
        System.out.println(event.toString());
    }

    /**
     * Prints the message when the user adds some Event.
     *
     * @param eventAdded the Event being added
     */
    public void printAddEventMessage(Event eventAdded) {
        System.out.println("I've added this Event: ");
        System.out.println(eventAdded.toString());
    }

    /**
     * Prints the message when the user adds some Event.
     *
     * @param eventEdited the Event being edited
     */
    public void printEditEventMessage(Event eventEdited) {
        System.out.println("The event has been edited.");
    }

    /**
     * Prints the message when the user sorts the events.
     */
    public void printSortEventMessage() {
        System.out.println("Got it. The events have been sorted.");
    }

    /**
     * Prints all the Events of the filtered list with labels, based on the input list.
     *
     * @param filteredEventList an ArrayList of Events to be printed one by one.
     */
    public void printFilteredDateEventList(ArrayList<Event> filteredEventList) {
        int numPrintedEvents = 0;

        System.out.println(" Here are the Events on the given date in your list:");
        for (Event event : filteredEventList) {
            numPrintedEvents++;
            System.out.println(numPrintedEvents + ". " + event.toString());
        }
    }

    /**
     * Prints all the locations stored in the location list with nearest bus stops.
     *
     * @param locationsList an Array List of Locations to be printed.
     */
    public void printLocationList(ArrayList<Location> locationsList) {
        int i = 1;
        System.out.println("Lists of locations: ");
        for (Location location : locationsList) {
            System.out.println(i + ". " + location);
            i++;
        }
    }


    /**
     * Prints all the bus stops stored in the bus stop list with buses that goes there.
     *
     * @param busStopList an Array List of Bus Stops to be printed
     */
    public void printBusStopList(ArrayList<BusStop> busStopList) {
        int i = 1;
        System.out.println("List of bus stops: ");
        for (BusStop busStop : busStopList) {
            System.out.println(i + ". " + busStop);
            i++;
        }
    }

    /**
     * Prints the help information when user input command 'help'.
     * Relevant command information with reference to the current user guide draft.
     */
    public void printHelp() {
        System.out.println("Current version: v2.1");
        System.out.println("Below are all the commands and brief descriptions of their functions in the form of "
                + "command - function: ");
        System.out.println("help - view all the commands");
        System.out.println("assignment - add an assignment event with content, deadline and location");
        System.out.println("class - add a class event with content, deadline and location");
        System.out.println("personalEvent - add a personal event with content, deadline and location");
        System.out.println("selfStudy - add a self directed study event");
        System.out.println("edit - edit event information");
        System.out.println("locate - find location information");
        System.out.println("locations - view all the locations available");
        System.out.println("list - view all events added");
        System.out.println("clear - clear all events");
        System.out.println("reminder - show events of the day");
        System.out.println("autoClear - clear all events one month ago");
        System.out.println("clearBefore - clear all events before the date input");
        System.out.println("studyTime - show the amount of study time detected by the program of the day");
        System.out.println("sort - sort all events according to the criteria given");
        System.out.println("repeat - repeat events that have previously happened");
        System.out.println("done - mark a certain task/event as done");
        System.out.println("date - show all events on the date given");
        System.out.println("There will be more upcoming features in later versions.");
        System.out.println("For more information, please refer to detailed user guide: "
                + "https://ay2021s1-cs2113t-f14-4.github.io/tp/");
    }

    /**
     * Prints the message when the user clears the list.
     */
    public void printClearEventsSuccessful() {
        System.out.println("Clear successful. The list is now empty.");
    }

    /**
     * Prints all the Events of the filtered list with labels, based on the input list.
     * Called for printing the events that having conflict timing with the new event added.
     *
     * @param conflictEvents an ArrayList of Events to be printed one by one.
     */
    public void printConflictEvents(ArrayList<Event> conflictEvents) {
        if (conflictEvents == null) {
            return;
        }
        if (conflictEvents.size() == 0) {
            return;
        }
        int numPrintedEvents = 0;
        System.out.println("You have some events that happen concurrently. Please prepared for multi-thread. "
                + "\n"
                + "Here are the Events in your list that have overlaps with your newly added/edited event:");
        for (Event event : conflictEvents) {
            numPrintedEvents++;
            System.out.println(numPrintedEvents + ". " + event.toString());
        }
        System.out.println("\nBut nonetheless...");
    }

    public void helloWithName(String name) {
        System.out.println("Hi " + name + ", nice to see you.");
    }

    /**
     * Prints the amount of time the person spent on study on that day.
     *
     * @param filteredEventList the list that contains events that are academic related, happen on that day, and have
     *                          been done.
     * @param date              the date that the user want to know his or her study time.
     */
    public void printStudyTime(ArrayList<Event> filteredEventList, LocalDate date) {
        int hour = 0;
        int minute = 0;
        int startHour;
        int endHour;
        int startMinute;
        int endMinute;
        for (Event event : filteredEventList) {
            if (event.getDate().isBefore(date)) {
                startHour = 0;
                startMinute = 0;
            } else {
                startHour = event.getStartDateTime().getHour();
                startMinute = event.getStartDateTime().getMinute();
            }

            if (event.getEndDate().isAfter(date)) {
                endHour = 24;
                endMinute = 0;
            } else {
                endHour = event.getEndDateTime().getHour();
                endMinute = event.getEndDateTime().getMinute();
            }

            hour += endHour - startHour;
            minute += endMinute - startMinute;
        }

        hour += minute / 60;
        minute %= 60;

        System.out.println("The amount of time you spent on study on that day is:\n"
                + hour + " hour(s) " + minute + " minute(s)");
    }

    /**
     * Print the Not Done events today.
     *
     * @param filteredEventList The events that are not done today.
     */
    public void printRemindEventList(ArrayList<Event> filteredEventList) {
        int numPrintedEvents = 0;

        System.out.println("Here are the events that you should do today:");
        for (Event event : filteredEventList) {
            numPrintedEvents++;
            System.out.println(numPrintedEvents + ". " + event.toString());
        }
    }

    /**
     * Print the success message after repeating all classes in the current week.
     *
     * @param numWeeks number of weeks to repeat.
     */
    public void printRepeatAll(int numWeeks) {
        System.out.println("All classes in this week have been repeated for " + numWeeks + " weeks.");
    }

    /**
     * Print the success message after repeating the selected event.
     *
     * @param numWeeks number of weeks to repeat.
     */
    public void printRepeatEvent(int numWeeks) {
        System.out.println("The selected event has been repeated for " + numWeeks + " weeks.");
    }

    /**
     * Print the success message after the user input auto clear.
     */
    public void printAutoClearOn() {
        System.out.println("Great! All events happened one month ago are cleared :)");
    }

    /**
     * Print the success message after the user successfully clear all events before a certain date.
     */
    public void printClearBefore(LocalDate clearDate) {
        System.out.println("Great! You have cleared all events before " + clearDate.toString());
    }

    public void printFassLocations() {
        System.out.println("Locations available at FASS are: \n"
                + "BLK/AS1~8 \n"
                + "L/LT8~15"
        );
    }

    public void printSdeLocations() {
        System.out.println("Locations available at SDE are: \n"
                + "BLK/SDE1~4 \n"
                + "BLK/CELC"
        );
    }

    public void printFosLocations() {
        System.out.println("Locations available at FOS are: \n"
                + "BLK/S1~17 \n"
                + "BLK/MD1~11 \n"
                + "L/LT20~34"
        );
    }

    public void printFoeLocations() {
        System.out.println("Locations available at FOE are: \n"
                + "BLK/E1~E6 \n"
                + "BLK/EA \n"
                + "BLK/E1A \n"
                + "BLK/E2A \n"
                + "BLK/E3A \n"
                + "BLK/E4A \n"
                + "BLK/EW1 \n"
                + "BLK/EW2 \n"
                + "BLK/IT \n"
                + "L/LT1~7 \n"
                + "L/LT7A"
        );
    }

    public void printHostelLocations() {
        System.out.println("Hostels in NUS are: \n"
                + "H/Raffles Hall \n"
                + "H/Kent Ridge Hall \n"
                + "H/King Edward VII Hall \n"
                + "H/Sheares Hall \n"
                + "H/Temasek Hall \n"
                + "H/Eusoff Hall \n"
                + "H/Cinnamon College \n"
                + "H/College of Alice & Peter Tan \n"
                + "H/Residential College 4 \n"
                + "H/Ridge View Residential College \n"
                + "H/Tembusu College \n"
                + "H/Kuok Foundation House \n"
                + "H/Prince George’s Park \n"
                + "H/UTown Residence"
        );
    }

    public void printSocAndBizLocations() {
        System.out.println("Locations available at SOC or BIZ are: \n"
                + "L/LT16~LT19 \n"
                + "BLK/COM1 \n"
                + "BLK/COM2 \n"
                + "BLK/BIZ1 \n"
                + "BLK/BIZ2 \n"
                + "BLK/Shaw Foundation Alumni House \n"
                + "BLK/I Cube");

    }

}

