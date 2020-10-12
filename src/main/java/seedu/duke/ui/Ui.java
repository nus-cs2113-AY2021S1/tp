package seedu.duke.ui;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import java.util.Scanner;

public class Ui {

    private static Scanner in = new Scanner(System.in);

    public static String getCommand() {
        return in.nextLine();
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help?");
    }

    public static void showError(Exception error) {
        System.out.println(error);
    }

    public static void commandHelp() {
        System.out.println("Here are the range of commands:\n"
                + "1./route : Displays possible direct bus from point to point\n"
                + "2./routemap: Displays the route map with its intermediate bus stops\n"
                + "3./bus: Displays buses available at each bus stop\n"
                + "4./allbus: Lists all buses available in NUS Zone a\n"
                + "5./liststops: Lists all bus stops in NUS Zone a\n"
                + "6./exit: Exit program\n"
                + "7./help: List all available commands\n");
    }

    public static void printExitMessage() {
        System.out.println("So long buddy!");
    }

    public static void printRouteSelectionMessage() {
        System.out.println("What bus routes would you like to see?\nCurrently, we have two bus routes available for"
                + "your viewing \n1.AA1 \n2.AA2 \nType the name to view:");
    }
}
