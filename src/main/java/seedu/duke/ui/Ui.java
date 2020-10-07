package seedu.duke.ui;

import seedu.duke.event.Event;

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

    public void printListMessage() {

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