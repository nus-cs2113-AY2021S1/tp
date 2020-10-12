package seedu.duke.ui;

import seedu.duke.Bus;

import java.util.ArrayList;
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

    public static void commandList() {
    }

    public static void printExitMessage() {
        System.out.println("So long buddy!");
    }

    public static void printRouteMessage(ArrayList<Bus> options) {
        int optionNo = 1;
        boolean isPossible = false;
        System.out.println("\nThe buses you can take are: ");
        for (Bus option: options) {
            if (option.toString() != null) {
                System.out.println(optionNo + ". " + option.toString());
                optionNo++;
                isPossible = true;
            }
        }
        if (!isPossible) {
            System.out.println("none.\n\tThere are no direct bus routes. :(");
        }
    }
}
