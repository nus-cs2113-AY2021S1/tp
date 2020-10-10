package seedu.duke;

import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.task.Task;

import java.io.IOException;
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
     * Lists all available commands to the user with the format of the command.
     */
    public static void printHelpCommand() {
        System.out.println("List of available commands:\n"
                + "1. todo <task description>\n"
                + "2. deadline <task description> /by ddMMyy\n"
                + "3. activity <activity description> <venue> /at ddMMyy\n"
                + "4. exam <module code> <venue> /at ddMMyy HHmm\n"
                + "5. lecture <module code> <venue> /at ddMMyy HHmm\n"
                + "6. tutorial ... / date time\n"
                + "7. lab ... / date time\n"
                + "8. done <task number>\n"
                + "9. delete <task number>\n"
                + "10. find <keyword>\n"
                + "11. print list\n"
                + "12. print events\n"
                + "13. print timeline\n"
                + "14. print progress"
        );
    }

    public static void printDateParseError() {
        System.out.println("Unable to parse date");
    }

    /**
     * Returns the input of the user.
     *
     * @return user's input
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Prints the Duke welcome message.
     */
    public static void printWelcomeMessage() {
        printDukeBorder(true);
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?");
        printDukeBorder(false);
    }

    /**
     * Prints the Duke exit message.
     */
    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
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
     * @param taskNumberDelete task number of the task to be deleted.
     * @param calendarList     task list of the task to be deleted.
     */
    public static void printDeleteTaskMessage(int taskNumberDelete, CalendarList calendarList) {
        /* - 1 is catered for array list's index starting from 0. */
        System.out.println("Task deleted:\n" + calendarList.getCalendarList().get(taskNumberDelete - 1));
        System.out.println("Your total tasks: " + (calendarList.getTotalTasks() - 1));
    }

    /**
     * Shows the user the list of tasks in the calendar list, formatted as an indexed list starting from 1.
     *
     * @param calendarList tasks retrieved from this task list.
     */
    public static void printTaskListView(CalendarList calendarList) {
        System.out.println("This is your list of task(s):");
        for (int i = 0; i < calendarList.getTotalTasks(); i++) {
            System.out.printf("%d." + calendarList.getCalendarList().get(i) + "\n", i + 1);
        }
    }

    /**
     * Shows the user all the event type of tasks in the task list,
     * such as lecture, lab, tutorial and events.
     *
     * @param calendarList tasks retrieved from this task list.
     */
    /*
    public static void printEventsListView(CalendarList calendarList) {
        int eventCounts = 0;
        System.out.println("This is your list of event(s):");
        for (int i = 0; i < calendarList.getTotalTasks(); i++) {
            if (calendarList.getCalendarList().get(i).getTaskType().equals("E")) {
                eventCounts++;
                System.out.printf("%d." + calendarList.getCalendarList().get(i) + "\n", eventCounts);
            }
        }
        if (eventCounts == 0) {
            System.out.println("Oops, there are no events stored in your list!");
        }
    }
    */

    /**
     * Shows the user the task (that was indicated by the user) that was marked as done .
     *
     * @param calendarList        calendar list that has the task marked as done.
     * @param taskNumberCompleted task number indicated by the user as done.
     */
    public static void printCompleteTaskMessage(int taskNumberCompleted, CalendarList calendarList) {
        System.out.println(
                "Good work! I've marked this task as done:\n"
                        + calendarList.getCalendarList().get(taskNumberCompleted - 1));
    }

    /**
     * Shows the user the task that was added and the total number of tasks in the task list.
     *
     * @param calendarList the calendar list that the task was added to.
     */
    public static void printAddTaskMessage(CalendarList calendarList) {
        System.out.println("Got it. I've added this task:");

        /* - 1 is catered for array list's index starting from 0. */
        System.out.println(calendarList.getCalendarList().get(calendarList.getCalendarList().size() - 1));

        System.out.println("Your total tasks: " + calendarList.getTotalTasks());
    }

    /**
     * Prints all tasks that contains the keyword, including the task index in the task list.
     *
     * @param calendarList the list of tasks being searched.
     * @param keyword      keyword indicated by user.
     * @throws DukeException if there are no tasks that contains the keyword.
     */
    public static void printFindTaskMessage(CalendarList calendarList, String keyword) throws DukeException {
        boolean isFound = false;

        for (int i = 0; i < calendarList.getTotalTasks(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (item instanceof Task) {
                if (((Task) item).getDescription().contains(keyword)) {
                    if (!isFound) { // first instance when keyword is found
                        System.out.println("Here are the matching tasks in your list:");
                    }
                    isFound = true;
                    System.out.println((i + 1) + "." + item);
                }
            }
        }
        if (!isFound) {
            throw new DukeException("keyword not found");
        }
    }

    /**
     * Show the user's progress on deadlines and todos.
     *
     * @param calendarList the list of user tasks.
     */
    public static void printProgress(CalendarList calendarList) {
        int numFinished = 0;
        int numTotal = 0;
        for (int i = 0; i < calendarList.getTotalTasks(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (item instanceof Task) {
                if (((Task) item).getTaskType().equals("E") || ((Task) item).getTaskType().equals("D")) {
                    numTotal++;
                    if (((Task) item).getIsDone()) {
                        numFinished++;
                    }
                }
            }
        }

        if (numTotal == 0) {
            System.out.println("You have no deadlines or todos now!");
        } else {
            float progress = (float) numFinished / numTotal * 100;
            System.out.println("You have finished " + numFinished + "/" + numTotal
                    + " (" + String.format("%.2f", progress) + "%) deadlines and todos.");
        }
    }

    /**
     * Prints the error message based on the invalid command input by the user.
     *
     * @param e            type of error.
     * @param calendarList the working calendar list.
     */
    public static void printDukeExceptionMessage(DukeException e, CalendarList calendarList) {
        switch (e.getException()) {
        case "todo":
            System.out.println("Error: The description of todo cannot be empty.");
            break;
        case "deadline":
            System.out.println("Error: Please key in the deadline in this format: deadline ... /by ddMMyy");
            break;
        case "activity":
            System.out.println("Error: Please key in the event in this format: event ... /at ddMMyy");
            break;
        case "lecture":
            System.out.println("Error: Please key in the lecture in this format: lecture ... / date time");
            break;
        case "tutorial":
            System.out.println("Error: Please key in the tutorial in this format: tutorial ... / date time");
            break;
        case "lab":
            System.out.println("Error: Please key in the lab in this format: lab ... / date time");
            break;
        case "exam":
            System.out.println("Error: Please key in the exam in this format: exam <module code> <exam details> /at "
                    + "ddMMyy HHmm");
            break;
        case "invalid command":
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "Type \"help\" to learn the different commands.");
            break;
        case "invalid task action":
            System.out.println("Error: Total task(s): " + calendarList.getTotalTasks());
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
        case "invalid done number":
            System.out.println("You can only mark a task as done. An event cannot be marked as done.");
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
     * Shows the user the exception that occurred when creating a storage file.
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

}
