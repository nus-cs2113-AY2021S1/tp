package seedu.modtracker;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the program.
 */
public class Ui {
    private static final Scanner in = new Scanner(System.in);
    public static final String INVALID_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ENTER_HELP = "Enter <help> for a quick view of available commands.";
    public static final String HELP_LIST = "Available Commands:\n"
            + "1. addmod <module code>\n"
            + "   example: addmod CS2113T\n"
            + "2. addexp <module code> <expected workload>\n"
            + "   example: addexp CS2113T 5\n"
            + "3. addtime <module code> <actual time spent> <week number>\n"
            + "   example: addtime CS2113T 2 1\n"
            + "4. list <week number>\n"
            + "   example: list 2\n"
            + "5. deletemod <module code>\n"
            + "   example: deletemod CS2113T\n"
            + "6. deleteexp <module code>\n"
            + "   example: deleteexp CS2113T\n"
            + "7. minus <module code> <time> <week number>\n"
            + "   example: minus CS2113T 2 1\n"
            + "8. exit\n";
    public static final String LOGO = "|\\\\        /|         |======            ||\n"
            + "||\\\\      / |  __   __|  ||  __  ___ ___ ||    ___   ____\n"
            + "|| \\\\    /  |//  \\//  |  ||//  \\/  |/    ||// / _ \\ //   \\\n"
            + "||  \\\\  /   |||   ||  |  |||   ||  ||    ||\\\\ | __/ ||\n"
            + "||   \\\\/    |\\\\__/\\\\__|  |||   \\\\__|\\___ || \\\\\\___| ||\n"
            + "*****************************************************|\n";

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
        System.out.println(INVALID_COMMAND);
        System.out.println(ENTER_HELP + System.lineSeparator());
    }

    /**
     * Prints the welcome screen when the program starts.
     */
    public void printWelcomeScreen() {
        System.out.println("Hello from" + System.lineSeparator() + LOGO);
        System.out.println("Full user guide available at: https://ay2021s1-cs2113t-f12-4.github.io/tp/");
        System.out.println(ENTER_HELP + System.lineSeparator());
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
     * @param week    specified week number.
     */
    public void printTable(ModuleList modList, int week) {
        ModView view = new ModView();
        view.printAllModuleInformation(modList, week);
    }

    /**
     * Prints all available commands.
     */
    public void printHelpList() {
        System.out.println(HELP_LIST);
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
        System.out.println(ENTER_HELP);
    }

    public void printBreakDownAnalysis(ModuleList modList, int week) {
        ViewTimeBreakDownAnalysis breakDown = new ViewTimeBreakDownAnalysis();
        breakDown.printTimeBreakDownAndAnalysis(modList, week);
    }

    /**
     * Prints the task added line when the user added a task to the task list.
     *
     * @param tasks task list
     */
    public void printTaskIsAdded(ArrayList<Task> tasks, String modCode) {
        System.out.println("Got it. I've added this task under " + modCode + ":");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    /**
     * Prints the number of tasks stored in the task list.
     *
     * @param tasks task list
     */
    public void printNumberOfTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("The current task list is empty.");
        } else if (tasks.size() > 1) {
            System.out.println("Now you have " + tasks.size() + " tasks in the list." + System.lineSeparator());
        } else {
            System.out.println("You currently have no task :-)" + System.lineSeparator());
        }
    }

    /**
     * Prints invalid task number when task number entered does not exist.
     */
    public void printInvalidTaskNumber(ArrayList<Task> tasks) {
        System.out.println("Invalid task number.");
        System.out.println("Enter a task number from 1 to " + tasks.size() + "." + System.lineSeparator());
    }

    /**
     * Prints out the task list.
     *
     * @param list task list
     */
    public void printTaskList(TaskList list) {
        ArrayList<Task> tasks = list.getTaskData();
        if (tasks.size() == 0) {
            System.out.println("The current task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i - 1));
            }
        }
        System.out.println(); //added a new line after the list
    }

}
