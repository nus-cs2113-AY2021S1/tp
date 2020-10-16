package seedu.duke;

import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Event;
import seedu.duke.calendar.task.Deadline;
import seedu.duke.calendar.task.Task;
import seedu.duke.command.CountdownCommand;
import seedu.duke.calendar.event.Exam;

import java.io.IOException;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    public static final String COMMAND_FIND_EVENT = "/fe";
    public static final String COMMAND_FIND_TASK = "/ft";
    public static final String COMMAND_FIND_EVENT_OR_TASK = "/f";
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
                + "2. deadline <task description> /ddMMyy\n"
                + "3. act <activity description> @<venue> /ddMMyy HHmm\n"
                + "4. exam <module code> @<venue> /ddMMyy HHmm\n"
                + "5. lect <module code> @<venue> /ddMMyy HHmm\n"
                + "6. tut <module code> @<venue> /ddMMyy HHmm\n"
                + "7. lab <module code> @<venue> /ddMMyy HHmm\n"
                + "8. done <task number>\n"
                + "9. -t <task number>\n"
                + "10. -e <event number>\n"
                + "11. /f <keyword of task/event>\n"
                + "12. /ft <keyword of task>\n"
                + "11. /fe <keyword of event>\n"
                + "12. print tasks\n"
                + "13. print events\n"
                + "14. print timeline\n"
                + "15. print progress\n"
                + "16. countdown exams\n"
                + "17. countdown deadlines"
        );
    }

    public static void printDateParseError() {
        System.out.println("Unable to parse date");
    }

    public static void printTotalTaskNumber(CalendarList calendarList) {
        System.out.println("Your total task(s): " + calendarList.getTotalTasks());
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
        System.out.println("Printing of 25/7 logo!!!!");
        System.out.println("===========================================================================\n"
                + "Welcome to 25/7 Task Manager!\n"
                + "What can I do for you?\n"
                + "Enter 'help' for the list of commands.\n"
                + "===========================================================================");

        /**
         //        String[]  HELLO_MESSAGE = {
         //
         "=================================================================================================",
         //                "   .-----------------.     .-----------------.              //    .-------------------.",
         //                "   |______________.  |     |  _______________|             //     |______________.   |",
         //                "                  |  |     |  |                           //                    /   /",
         //                "                  |  |     |  |                          //                    /   /",
         //                "   .---------------  |     |  |---------------.         //                    /   /",
         //                "   | ________________|     |________________  |        //                    /   /",
         //                "   | |                                     |  |       //                    /   /",
         //                "   | |                                     |  |      //                    /   /",
         //                "   | ----------------.     .---------------|  |     //                    /   /",
         //                "   |_________________|     |__________________|    //                    /___/",
         //                " ",
         //
         "=================================================================================================",
         //                " Welcome to 25/7 Task Manager!",
         //                " What can I do for you?",
         //                " Enter 'help' for the list of commands.",
         //
         "================================================================================================="
         //        };
         //        System.out.println(String.join("\n", HELLO_MESSAGE));
         */

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
            System.out.println("............................. DUKE CHAT BOX ^^ ............................");
        } else {
            System.out.println("...........................................................................");
        }
    }

    /**
     * Shows the task deleted and the number of tasks left in the list.
     *
     * @param numberDelete task number of the task to be deleted.
     * @param calendarList task list of the task to be deleted.
     */
    public static void printDeleteMessage(int numberDelete, CalendarList calendarList) {
        System.out.println("Deleted:\n" + calendarList.getCalendarList().get(numberDelete));
    }

    /**
     * Shows the user the list of items in the calendar list,
     * formatted as an indexed list starting from 1.
     *
     * @param calendarList tasks retrieved from this task list.
     */
    public static void printTaskListView(CalendarList calendarList) {
        int taskCounts = 0;
        System.out.println("This is your list of task(s):");
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            if (calendarList.getCalendarList().get(i) instanceof Task) {
                taskCounts++;
                System.out.printf("%d." + calendarList.getCalendarList().get(i) + "\n", taskCounts);
            }
        }
        if (taskCounts == 0) {
            System.out.println("Oops, there are no tasks stored in your list!");
        }
    }

    /**
     * Shows the user all the events in the calendar list,
     * such as lecture, lab, tutorial and events.
     *
     * @param calendarList tasks retrieved from this task list.
     */
    public static void printEventsListView(CalendarList calendarList) {
        int eventCounts = 0;
        System.out.println("This is your list of event(s):");
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            if (calendarList.getCalendarList().get(i) instanceof Event) {
                eventCounts++;
                System.out.printf("%d." + calendarList.getCalendarList().get(i) + "\n", eventCounts);
            }
        }
        if (eventCounts == 0) {
            System.out.println("Oops, there are no events stored in your list!");
        }
    }

    /**
     * Shows the user the task (that was indicated by the user) that was marked as done .
     *
     * @param calendarNumberCompleted calendar number of the task set as done.
     * @param calendarList            calendar list that has the task marked as done.
     */
    public static void printCompleteTaskMessage(int calendarNumberCompleted, CalendarList calendarList) {
        System.out.println(
                "Good work! I've marked this task as done:\n"
                        + calendarList.getCalendarList().get(calendarNumberCompleted));
    }

    /**
     * Shows the user the task/event that was added.
     *
     * @param calendarList the calendar list that the task was added to.
     */
    public static void printAddMessage(CalendarList calendarList, boolean isTask) {
        String calendarItem;
        if (isTask) {
            calendarItem = "task";
        } else {
            calendarItem = "event";
        }
        System.out.println("Got it. I've added this " + calendarItem + ":");

        /* - 1 is catered for array list's index starting from 0. */
        int lastCalendarItemIndex = calendarList.getCalendarList().size() - 1;

        System.out.println(calendarList.getCalendarList().get(lastCalendarItemIndex));
    }

    /**
     * Prints each item's countdown.
     *
     * @param days how many days left.
     * @param item the item to print the countdown.
     */
    public static void printCountDownItem(int days, CalendarItem item) {
        if (days < 0) {
            System.out.println(item.getDescription() + " You have already missed it!");
        } else if (days == 0) {
            if (item instanceof Exam) {
                System.out.println(item.getDescription() + " is in a day. Try your best!");
            } else {
                System.out.println(item.getDescription() + " is in a day. It's time to speed up!");
            }
        } else {
            System.out.println(item.getDescription() + " has " + days + " days left.");
        }
    }

    /**
     * Print the countdown for every item in the calendar list.
     *
     * @param calendarList the calendar list we want to print the countdown for.
     * @param type 0 is for exam events, 1 is for deadline tasks.
     */
    public static void printCountDownMessage(CalendarList calendarList, int type) {
        switch (type) {
        case 0:
            System.out.println("Here is your exams countdown:");
            for (int i = 0; i < calendarList.getTotalItems(); i++) {
                CalendarItem temp = calendarList.getItem(i);
                Ui.printCountDownItem(((Exam)temp).getCountdown(), temp);
            }
            break;
        case 1:
            System.out.println("Here is your deadlines countdown:");
            for (int i = 0; i < calendarList.getTotalItems(); i++) {
                CalendarItem temp = calendarList.getItem(i);
                Ui.printCountDownItem(((Deadline)temp).getCountdown(), temp);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Prints the calendar task/event/item for FindCommand.
     *
     * @param command        command type.
     * @param calendarList   the calendar list to search from.
     * @param isFound        true if the first item has been found and printed.
     * @param itemIndex      item index in the calendar list.
     * @param printNumbering item index printed to the user.
     */
    public static void printFindTaskMessage(String command, CalendarList calendarList, boolean isFound,
                                            int itemIndex, int printNumbering) {

        if (!isFound) { // first instance when keyword is found
            String itemType = "";
            switch (command) {
            case COMMAND_FIND_EVENT:
                itemType = "event(s)";
                break;
            case COMMAND_FIND_TASK:
                itemType = "task(s)";
                break;
            case COMMAND_FIND_EVENT_OR_TASK:
                itemType = "item(s)";
                break;
            default:
                break;
            }
            System.out.println("Here are the matching " + itemType + " in your calendar:");
        }
        System.out.printf("%d." + calendarList.getCalendarList().get(itemIndex) + "\n", printNumbering);
    }

    /**
     * Show the user's progress on deadlines and todos.
     *
     * @param calendarList the list of user tasks.
     */
    public static void printProgress(CalendarList calendarList) {
        int numFinished = 0;
        int numTotal = 0;
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (!(item instanceof Task)) {
                continue;
            }
            if (((Task) item).getTaskType().equals("D") || ((Task) item).getTaskType().equals("T")) {
                numTotal++;
                if (((Task) item).getIsDone()) {
                    numFinished++;
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
            System.out.println("Error: Please key in the deadline in this format: deadline <task description> /ddMMyy");
            break;
        case "activity":
            System.out.println("Error: Please follow this format: act <activity description> @<venue> /ddMMyy HHmm");
            break;
        case "lecture":
            System.out.println("Error: Please key in the lecture in this format: lect <module code> @<venue> /"
                    + "ddMMyy HHmm");
            break;
        case "tutorial":
            System.out.println("Error: Please key in the tutorial in this format: tut <module code> @<venue> /"
                    + "ddMMyy HHmm");
            break;
        case "lab":
            System.out.println("Error: Please key in the lab in this format: lab <module code> @<venue> /"
                    + "ddMMyy HHmm");
            break;
        case "exam":
            System.out.println("Error: Please key in the exam in this format: exam <module code> @<exam venue> /"
                    + "ddMMyy HHmm");
            break;
        case "invalid command":
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "Type \"help\" to learn the different commands.");
            break;
        case "invalid countdown":
            System.out.println("Error: invalid countdown. Countdown is only for exams and deadlines.");
            break;
        case "invalid task action":
            System.out.println("Error: Total task(s): " + calendarList.getTotalTasks());
            break;
        case "invalid event action":
            System.out.println("Error: Total event(s): " + calendarList.getTotalEvents());
            break;
        case "done":
            System.out.println("Error: Please key in the command in this format: done <task number>");
            break;
        case "delete":
            System.out.println("Error: Please key in the command in this format: -t <task number> "
                    + "OR -e <event number>");
            break;
        case "keyword not found":
            System.out.println("There are no tasks matching this keyword. Check that you have spelt it correctly.");
            break;
        case "file not found":
            System.out.println("The file can not be found");
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
     * Shows the user the exception that occurred when creating a storage file.
     *
     * @param e exception message.
     */
    public static void printFileCreateErrorMessage(IOException e) {
        System.out.println("Cannot create file; reason: " + e.getMessage());
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
     * Shows the user the exception that occurred when finding the storage file.
     */
    public static void printFileNotFoundErrorMessage() {
        System.out.println("File not found.");
    }

    /**
     * Shows the user the exception that occurred when when there is an invalid command message.
     */
    public static void printInvalidFileCommandMessage() {
        System.out.println("Invalid file command input");
    }
}
