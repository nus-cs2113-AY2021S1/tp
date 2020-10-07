package seedu.duke.ui;

import seedu.duke.event.Event;
import seedu.duke.event.EventList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);

    }

    public void printDividerLine() {
        System.out.println("_________________________________");
    }

    public void printWelcomeMessage() {
        printDividerLine();
        System.out.println("Welcome to scheduler--;!");
        System.out.println("What can I do for you?");
        printDividerLine();
    }

    public void printByeMessage() {
        printDividerLine();
        System.out.println("Thank you for using scheduler--;!");
        System.out.println("We hope to see you soon!");
        printDividerLine();
    }

    public String receiveCommand() {
        return in.nextLine();
    }

    public void printEventAddedMessage(Event event) {
        System.out.println("You have successfully added this event to your list!");
        System.out.println(event);
        printDividerLine();
    }

    public void printRepeatEventMessage() {

    }

    public void printGoalMessage(Event goal) {
        if (goal != null) {
            System.out.println("Goal: " + goal);
        } else {
            System.out.println("You have no goal! Why not set one now?");
        }
    }

    public void printChangeGoalMessage(Event goal) {
        System.out.println("Goal changed to: " + goal);
    }

    public void printCheckMessage() {

    }

    public void printSpecificList(String eventType, EventList eventList) {
        int eventCount = 0;
        System.out.println("Here is a list of your " + eventType + " events:");
        for (Event event : eventList.getEvents()) {
            eventCount++;
            System.out.println(eventCount + ". " + event.toString());
        }
    }

    public void printList(ArrayList<EventList> eventLists) {
        int eventCount = 0;
        System.out.println("Here is a list of all your events!");
        for (EventList eventList : eventLists) {
            if (eventList.getEvents().size() > 0) {
                System.out.println("Under " + eventList.getName() + " events, you have: ");
                for (Event event : eventList.getEvents()) {
                    eventCount++;
                    System.out.println(eventCount + ". " + event.toString());
                }
            } else {
                System.out.println("You have no events under " + eventList.getName() + ".");
            }
            eventCount = 0;
        }
        printDividerLine();
    }

    public void printDeadlineChangedMessage(Event eventUpdated) {
        System.out.println("You have successfully updated the deadline for this event!");
        System.out.println(eventUpdated);
    }

    public void printStorageSavedMessage() {
        System.out.println("The file has successfully been saved!");
    }

    public void printStorageLoadingErrorMessage() {
        System.out.println("The file does not exist or has been corrupted!");
    }

    public void printInvalidDateMessage() {
        System.out.println("The date entered is invalid!");
    }

    public void printInvalidCommandMessage() {
        System.out.println("Sorry, I did not understand your command!");
    }

    /**
     * Prints exception message.
     *
     * @param exceptionMessage String of warning message from various exceptions.
     */
    public void printExceptionMessage(String exceptionMessage) {
        printDividerLine();
        System.out.println(exceptionMessage);
        printDividerLine();
    }


}