package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the program.
 */
public class Ui {
    private static Scanner in = new Scanner(System.in);

    /**
     * Reads input entered by the user.
     *
     * @return input entered by the user
     */
    public static String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the invalid command line.
     */
    public static void printInvalidCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(" + System.lineSeparator());
    }

    /**
     * Prints the welcome screen when the program starts.
     */
    public static void printWelcomeScreen() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
    }

    /**
     * Prints the exit line when user entered "bye".
     */
    public static void printExitScreen() {
        System.out.println("All changes saved.");
        System.out.println("Bye. Hope to see you again soon!" + System.lineSeparator());
    }
}
