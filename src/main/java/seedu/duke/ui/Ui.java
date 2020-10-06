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

    public void printEventAddedMessage() {

    }

    public void printRepeatEventMessage() {

    }

    public void printGoalAddedMessage() {

    }

    public void printCheckMessage() {

    }

    public void printList(ArrayList<EventList> eventLists) {
        int eventCount = 0;
        System.out.println("Here is a list of all your events!");
        for (EventList eventList : eventLists) {
            System.out.println("Under " + eventList.getName() + " events, you have: ");
            for (Event event : eventList.getEvents()) {
                eventCount++;
                System.out.println(eventCount + " " + event.toString());
            }
            eventCount = 0;
        }
    }

    public void printDeadlineChangedMessage() {

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