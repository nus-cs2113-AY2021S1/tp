package seedu.modtracker;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the program.
 */
public class Ui {
    private static final Scanner in = new Scanner(System.in);

    /**
     * Reads input entered by the user.
     *
     * @return input entered by the user
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the invalid command line.
     */
    public void printInvalidCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(" + System.lineSeparator());
    }

    /**
     * Prints the welcome screen when the program starts.
     */
    public void printWelcomeScreen() {
        String logo = "|\\\\        /|         |======            ||\n"
                + "||\\\\      / |  __   __|  ||  __  ___ ___ ||    ___   ____\n"
                + "|| \\\\    /  |//  \\//  |  ||//  \\/  |/    ||// / _ \\ //   \\\n"
                + "||  \\\\  /   |||   ||  |  |||   ||  ||    ||\\\\ | __/ ||\n"
                + "||   \\\\/    |\\\\__/\\\\__|  |||   \\\\__|\\___ || \\\\\\___| ||\n"
                + "*****************************************************|\n";

        System.out.println("Hello from" + System.lineSeparator() + logo);
        System.out.println("Full user guide available at: https://ay2021s1-cs2113t-f12-4.github.io/tp/");
        System.out.println("Enter <help> for a quick view of available commands." + System.lineSeparator());
    }

    /**
     * Prompts the user to enter his/her name.
     */
    public String printNamePrompt() {
        System.out.println("What is your name?");
        String name = in.nextLine();
        if (name.isEmpty()) {
            printInvalidCommand();
            printNamePrompt();
        } else {
            System.out.println("");
            System.out.println("Hello " + name + "!");
            System.out.println("What can I do for you?" + System.lineSeparator());
        }
        return name;
    }

    /**
     * Prints the exit line when user entered "bye".
     */
    public void printExitScreen(String name) {
        System.out.println("All changes saved.");
        System.out.println("Bye " + name + ". Hope to see you again soon!" + System.lineSeparator());
    }

    /**
     * Prints all the module information in a table.
     *
     * @param modList list of modules.
     * @param week specified week number.
     */
    public void printTable(ModuleList modList, int week) {
        ModView view = new ModView();
        view.printAllModuleInformation(modList,week);
    }

    /**
     * Prints all available commands.
     */
    public void printHelpList() {
        HelpList help = new HelpList();
        help.listCommands();
    }

    /**
     * Prints the message when module does not exist.
     */
    public void printNotExist(String moduleCode) {
        System.out.println(moduleCode + " does not exist." + System.lineSeparator());
    }

    /**
     * Prints the message when module exists.
     */
    public void printExist(String moduleCode) {
        System.out.println(moduleCode + " already exists." + System.lineSeparator());
    }

    /**
     * Prints the message when module is deleted.
     */
    public void printDelete(String moduleCode) {
        System.out.println(moduleCode + " is removed." + System.lineSeparator());
    }

    /**
     * Prints the message when module is deleted.
     */
    public void printDeleteExp(String moduleCode) {
        System.out.println("Expected Workload of " + moduleCode + " is removed." + System.lineSeparator());
    }

    /**
     * Prints the message when module is added to modList.
     */
    public void printAdd(Module moduleDetail) {
        System.out.println(moduleDetail + " is added." + System.lineSeparator());
    }

    /**
     * Prints the given error message.
     */
    public void printErrorMessage(String message) {
        System.out.println("There was an error :(");
        System.out.println(message);
    }
}
