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
     * Prints out the task list.
     *
     * @param modList task list
     */
    public static void printList(ArrayList<Module> modList) {
        for (int i = 0; i < ModuleList.modList.size(); i++) {
            System.out.println(ModuleList.modList.get(i));
        }
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
    }

    /**
     * Prompts the user to enter his/her name.
     */
    public static void printNamePrompt() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = scan.nextLine();
        if (name.isEmpty()) {
            Ui.printInvalidCommand();
            printNamePrompt();
        } else {
            System.out.println("");
            System.out.println("Hello " + name);
            System.out.println("What can I do for you?" + System.lineSeparator());
        }
    }

    /**
     * Prints the exit line when user entered "bye".
     */
    public static void printExitScreen() {
        System.out.println("All changes saved.");
        System.out.println("Bye. Hope to see you again soon!" + System.lineSeparator());
    }
}
