package seedu.duke.ui;


import NUSchedule.Task.Task;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI receives all user inputs, and produces outputs show to the user.
 */
public class UI {

    public static final String LOGO = " _       _ _        _   ___       _                    _        _     \n"
                                    + "| |     | | |      | | /___|     | |                  | |      | |   \n"
                                    + "|  \\    | | |      | |//         | |                  | |      | |\n"
                                    + "| |\\\\   | | |      | |\\\\         | |        ___       | |      | | ___ \n"
                                    + "| | \\\\  | | |      | | \\\\     ___| |______ /___\\  ____| |_    _| |/___\\       \n"
                                    + "| |  \\\\ | | |      | |  \\\\   /___|  ____  |<___>>|  __  | |  | | |<___>> \n"
                                    + "| |   \\\\| | |      | |   \\\\ //   | |    | | ___/ | |  | | |  | | | ___/    \n"
                                    + "| |    \\  | |______| |___// \\\\___| |    | |\\____ | |  | | |__| | |\\____\n"
                                    + "|_|     |_|__________|___/   \\___|_|    |_|\\___/ |______|\\___,_|_|\\___/           \n";
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final Scanner in;

    public UI() {
        in = new Scanner(System.in);
    }

    /**
     * Reads the user input line by line.
     *
     * @return the string of the line
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the logo of DUKE and greet the user.
     */
    public void printGreetingMessage() {
        printLine();

        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the separator.
     */
    public void printLine() {
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Prints all the events with labels, based on the input list.
     *
     * @param tasks an <\code>ArrayList</\code> of events to be printed one by one.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        int numPrintedTasks = 0;

        System.out.println(" Here are the tasks in your list:");
        for (Task task : tasks) {
            numPrintedTasks++;
            System.out.println(numPrintedTasks + ". " + task.toString());
        }
    }

    /**
     * Prints all the tasks of the filtered list with labels, based on the input list.
     *
     * @param tasks an <\code>ArrayList</\code> of tasks to be printed one by one
     */
    public void printFilteredTaskList(ArrayList<Task> tasks) {
        int numPrintedTasks = 0;

        System.out.println(" Here are the matching tasks in your list:");
        for (Task task : tasks) {
            numPrintedTasks++;
            System.out.println(numPrintedTasks + ". " + task.toString());
        }
    }

    /**
     * Shows the error message when experiencing exceptions.
     *
     * @param message the message get from the error
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows the error message during loading. Since the general IO exception is
     * handled when executing the process, the error leads to this would be the file
     * is edited in a wrong way.
     */
    public void showLoadingError() {
        System.out.println("You edit the file in a wrong format. Please check.");
    }

    /**
     * Prints the message during executing commands.
     * This function is used to make all printing being done in UI.
     *
     * @param message determined by the command
     */
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Call when exit the program
     */
    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Take in a size and prints the total number of tasks.
     *
     * @param size get by calling <code>ArrayList<Event>::Size()</code>
     */
    public void printNumTask(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the message when the user mark some task as done.
     *
     * @param task the task being done
     */
    public void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    /**
     * Prints the message when the user deletes some task.
     *
     * @param task the task being deleted
     */
    public void printDeleteMessage(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task.toString());
    }

    /**
     * Prints the message when the user adds some task.
     *
     * @param taskAdded the task being added
     */
    public void printAddTaskMessage(Task taskAdded) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(taskAdded.toString());
    }
    /**
     * Prints all the tasks of the filtered list with labels, based on the input list.
     *
     * @param filteredTaskList an <\code>ArrayList</\code> of tasks to be printed one by one
     */
    public void printFilteredDateTaskList(ArrayList<Task> filteredTaskList) {
        int numPrintedTasks = 0;

        System.out.println(" Here are the tasks on the given date in your list:");
        for (Task task : filteredTaskList) {
            numPrintedTasks++;
            System.out.println(numPrintedTasks + ". " + task.toString());
        }
    }





}
