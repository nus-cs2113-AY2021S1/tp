package seedu.duke;

import seedu.duke.Task.Task;
import seedu.duke.Task.TaskList;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Returns the input of the user.
     *
     * @return user's input
     */
    public String readCommand() {
//        String userInput;
//        try {
//            userInput = in.nextLine().trim();
//        }
//        catch (NoSuchElementException e)
        return in.nextLine().trim();
    }

    /**
     * Prints the Duke welcome message.
     */
    public static void printWelcomeMessage() {
        printDukeBorder(true);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?");
        printDukeBorder(false);
    }

    /**
     * Prints the Duke exit message.
     */
    public static void printExitMessage() {
        printDukeBorder(true);
        System.out.println("Bye. Hope to see you again soon!\n");
        printDukeBorder(false);
    }

    /**
     * Prints the border of the chat box.
     *
     * @param top set to true to print the top border. Else, set to false to print the bottom border
     */
    public static void printDukeBorder(boolean top) {
        if (top) {
            System.out.println("............. DUKE CHAT BOX ^^ ............");
        } else {
            System.out.println("...........................................");
        }
    }

    /**
     * Shows the task deleted and the number of tasks left in the list.
     *
     * @param taskNumberDelete
     * @param taskList
     */
    public static void printDeleteTaskMessage(int taskNumberDelete, TaskList taskList) {
        /* - 1 is catered for array list's index starting from 0. */
        System.out.println("Task deleted:\n" + taskList.getTaskList().get(taskNumberDelete - 1));
        System.out.println("Your total tasks: " + (taskList.getTotalTask() - 1));
    }

    /**
     * Shows the user the list of tasks in the task list, formatted as an indexed list starting from 1.
     *
     * @param taskList tasks retrieved from this task list.
     */
    public static void printTaskListView(TaskList taskList) {
        System.out.println("This is your list of task(s):");
        for (int i = 0; i < taskList.getTotalTask(); i++) {
            System.out.printf("%d." + taskList.getTaskList().get(i) + "\n", i + 1);
        }
    }

    /**
     * Shows the user the task (that was indicated by the user) that was marked as done .
     *
     * @param taskNumberCompleted task number indicated by the user as done.
     */
    public static void printCompleteTaskMessage(int taskNumberCompleted, TaskList taskList) {
        System.out.println("Good work! I've marked this task as done:\n" + taskList.getTaskList().get(taskNumberCompleted - 1));
    }

    /**
     * Shows the user the task that was added and the total number of tasks in the task list.
     *
     * @param taskList the list of task that the task was added to.
     */
    public static void printAddTaskMessage(TaskList taskList) {
        System.out.println("Got it. I've added this task:");

        /* - 1 is catered for array list's index starting from 0. */
        System.out.println(taskList.getTaskList().get(taskList.getTaskList().size() - 1));

        System.out.println("Your total tasks: " + taskList.getTotalTask());
    }

    /**
     * Prints all tasks that contains the keyword, including the task index in the task list.
     *
     * @param taskList the list of tasks being searched.
     * @param keyword keyword indicated by user.
     * @throws DukeException if there are no tasks that contains the keyword.
     */
    public static void printFindTaskMessage(TaskList taskList, String keyword) throws DukeException {
        boolean isFound = false;

        for (int i = 0; i < taskList.getTotalTask(); i++) {
            Task task = taskList.getTaskList().get(i);
            if (task.getDescription().contains(keyword)) {
                if (!isFound) { // first instance when keyword is found
                    System.out.println("Here are the matching tasks in your list:");
                }
                isFound = true;
                System.out.println((i + 1) + "." + task);
            }
        }
        if (!isFound) {
            throw new DukeException("keyword not found");
        }
    }

    /**
     * Prints the error message based on the invalid command input by the user.
     *
     * @param e        type of error.
     * @param taskList the working list of task.
     */
    public static void printDukeExceptionMessage(DukeException e, TaskList taskList) {
        switch (e.getException()) {
        case "todo":
            System.out.println("Error: The description of todo cannot be empty.");
            break;
        case "deadline":
            System.out.println("Error: Please key in the deadline in this format: deadline ... /by ...");
            break;
        case "event":
            System.out.println("Error: Please key in the event in this format: event ... /at ...");
            break;
        case "invalid command":
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "Available commands: list, done, todo, deadline, event");
            break;
        case "invalid task action":
            System.out.println("Error: Total task(s): " + taskList.getTotalTask());
            break;
        case "done":
            System.out.println("Error: Please key in the command in this format: done <task number>");
            break;
        case "delete":
            System.out.println("Error: Please key in the command in this format: delete <task number>");
            break;
        case "keyword not found":
            System.out.println("There are no tasks matching this keyword. Check that you have spelt it correctly.");
            break;
        default:
            System.out.println("Unknown Error.");
            break;
        }
    }

    /**
     * Shows the user the exception that occurred when saving data to storage file.
     *
     * @param e exception message.
     */
    public static void printSaveDataErrorMessage(IOException e) {
        System.out.println("Unable to save data. Error: " + e.getMessage());
    }

    /**
     * Prints the message to inform the user that no data was imported.
     */
    public static void printNoImportDataMessage() {
        System.out.println("No existing data imported.");
    }

    /**
     * Prints the message to inform the user that existing data was imported.
     */
    public static void printImportDataSuccessMessage() {
        System.out.println("Existing data imported.");
    }

    /**
     * Shows the user the exception that occurred when creating a storage file
     *
     * @param e exception message.
     */
    public static void printFileCreateErrorMessage(IOException e) {
        System.out.println("Cannot create file; reason: " + e.getMessage());
    }

    /**
     * Prints the message to inform the user that an output file is created.
     */
    public static void printFileCreatedMessage() {
        System.out.println("New output file created.");
    }

    /**
     * Prints the message to inform the user that the storage file was overwritten.
     */
    public static void printFileOverwriteMessage() {
        System.out.println("File exists. Data overwrite.");
    }
}
