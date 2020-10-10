package NUSchedule.ui;


import NUSchedule.event.Event;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI receives all user inputs, and produces outputs show to the user.
 */
public class UI {

    public static final String LOGO = " _       _ _        _   ____       _                    _        _     \n"
                                    + "| |     | | |      | | / ___|     | |                  | |      | |   \n"
                                    + "|  \\    | | |      | |/ /         | |                  | |      | |\n"
                                    + "| |\\\\   | | |      | |\\ \\         | |        ___       | |      | | ___ \n"
                                    + "| | \\\\  | | |      | | \\ \\     ___| |______ /___\\  ____| |_    _| |/___\\       \n"
                                    + "| |  \\\\ | | |      | |  \\ \\   /___|  ____  ||___|||  __  | |  | | ||___|| \n"
                                    + "| |   \\\\| | |      | |   \\ \\ //   | |    | | ___/ | |  | | |  | | | ___/    \n"
                                    + "| |    \\  | |______| |___/ / \\\\___| |    | |\\____ | |__| | |__| | |\\____\n"
                                    + "|_|     |_|__________|____/   \\___|_|    |_|\\___/ |______|\\___,_|_|\\___/           \n";
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final Scanner in;

    public UI() {
        in = new Scanner(System.in);
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
    public void printGreetingMessage() {
        printLine();

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
     * @param Events an <\code>ArrayList</\code> of events to be printed one by one.
     */
    public void printEventList(ArrayList<Event> Events) {
        int numPrintedEvents = 0;

        System.out.println(" Here are the Events in your list:");
        for (Event Event : Events) {
            numPrintedEvents++;
            System.out.println(numPrintedEvents + ". " + Event.toString());
        }
    }

    /**
     * Prints all the Events of the filtered list with labels, based on the input list.
     *
     * @param Events an <\code>ArrayList</\code> of Events to be printed one by one
     */
    public void printFilteredEventList(ArrayList<Event> Events) {
        int numPrintedEvents = 0;

        System.out.println(" Here are the matching Events in your list:");
        for (Event Event : Events) {
            numPrintedEvents++;
            System.out.println(numPrintedEvents + ". " + Event.toString());
        }
    }

    /**
     * Shows the error message when experiencing exceptions.
     *
     * @param message the message get from the error
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
     * Call when exit the program
     */
    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Take in a size and prints the total number of Events.
     *
     * @param size get by calling <code>ArrayList<Event>::Size()</code>
     */
    public void printNumEvent(int size) {
        System.out.println("Now you have " + size + " Events in the list.");
    }

    /**
     * Prints the message when the user mark some Event as done.
     *
     * @param Event the Event being done
     */
    public void printDoneMessage(Event Event) {
        System.out.println("Nice! I've marked this Event as done: ");
        System.out.println(Event.toString());
    }

    /**
     * Prints the message when the user deletes some Event.
     *
     * @param Event the Event being deleted
     */
    public void printDeleteMessage(Event Event) {
        System.out.println("Noted. I've removed this Event: ");
        System.out.println(Event.toString());
    }

    /**
     * Prints the message when the user adds some Event.
     *
     * @param EventAdded the Event being added
     */
    public void printAddEventMessage(Event EventAdded) {
        System.out.println("Got it. I've added this Event: ");
        System.out.println(EventAdded.toString());
    }
    /**
     * Prints all the Events of the filtered list with labels, based on the input list.
     *
     * @param filteredEventList an <\code>ArrayList</\code> of Events to be printed one by one
     */
    public void printFilteredDateEventList(ArrayList<Event> filteredEventList) {
        int numPrintedEvents = 0;

        System.out.println(" Here are the Events on the given date in your list:");
        for (Event Event : filteredEventList) {
            numPrintedEvents++;
            System.out.println(numPrintedEvents + ". " + Event.toString());
        }
    }





}
