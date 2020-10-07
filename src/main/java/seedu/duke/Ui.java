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
        for (int i = 0; i < modList.size(); i++) {
            System.out.println(modList.get(i));
        }
        System.out.println("");
    }

    /**
     * Prints the welcome screen when the program starts.
     */
    public static void printWelcomeScreen() {
        String logo = "|\\\\        /|         |======            ||\n"
                + "||\\\\      / |  __   __|  ||  __  ___ ___ ||    ___   ____\n"
                + "|| \\\\    /  |//  \\//  |  ||//  \\/  |/    ||// / _ \\ //   \\\n"
                + "||  \\\\  /   |||   ||  |  |||   ||  ||    ||\\\\ | __/ ||\n"
                + "||   \\\\/    |\\\\__/\\\\__|  |||   \\\\__|\\___ || \\\\\\___| ||\n"
                + "*****************************************************|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("User guide available at: https://ay2021s1-cs2113t-f12-4.github.io/tp/" + System.lineSeparator());
    }

    /**
     * Prompts the user to enter his/her name.
     */
    public static void printNamePrompt() {
        System.out.println("What is your name?");
        String name = in.nextLine();
        if (name.isEmpty()) {
            Ui.printInvalidCommand();
            printNamePrompt();
        } else {
            System.out.println("");
            System.out.println("Hello " + name + "!");
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

    /**
     * Prints the a table.
     *
     * @param modList list of modules.
     * @param week specified week number.
     */
    public static void printTable(ArrayList<Module> modList, int week) {
        View view = new View();
        view.printAllModuleInformation(modList,week);
    }
}
