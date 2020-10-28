package seedu.duke;

import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Event;
import seedu.duke.calendar.event.Exam;
import seedu.duke.calendar.event.Lab;
import seedu.duke.calendar.event.Lecture;
import seedu.duke.calendar.event.Tutorial;
import seedu.duke.calendar.task.Deadline;
import seedu.duke.calendar.task.Task;

import java.io.IOException;
import java.util.ArrayList;
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
                + "5. lect <module code> @<venue> -r <number of lecture> /ddMMyy HHmm\n"
                + "6. tut <module code> @<venue> -r <number of tutorial> /ddMMyy HHmm\n"
                + "7. lab <module code> @<venue> -r <number of lab> /ddMMyy HHmm\n"
                + "8. done <task number>\n"
                + "9. -t <task number>\n"
                + "10. -e <event number>\n"
                + "11. *t <task number>\n"
                + "12. /f <keyword of task/event>\n"
                + "13. /ft <keyword of task>\n"
                + "14. /fe <keyword of event>\n"
                + "15. print tasks\n"
                + "16. print events\n"
                + "17. print timeline <week/month/>\n"
                + "18. print progress\n"
                + "19. print *\n"
                + "20. countdown exams\n"
                + "21. countdown deadlines\n"
                + "22. /a <event number> - information\n"
                + "23. /v <event number>\n"
                + "24. suggestion"
        );
    }

    /**
     * Prints the number of tasks in the calendar list.
     *
     * @param calendarList containing the tasks.
     */
    public static void printTotalTaskNumber(CalendarList calendarList) {
        System.out.println("Your total task(s): " + calendarList.getTotalTasks());
    }

    /**
     * Prints the file not found message.
     */
    public static void printFileNotFoundMessage() {
        System.out.println("The file cannot be found: You should follow the UG's quick "
                + "start to normally access the file");
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
     * Prints the welcome message.
     */
    public static void printWelcomeMessage() {

        String hi = " #####  #######   #     # ####### #     # ######   #####       #      ######     #   #     #\n"
                + "#     # #         #     # #     # #     # #     # #     #     # #     #     #   # #   #   #\n"
                + "      # #         #     # #     # #     # #     # #          #   #    #     #  #   #   # #\n"
                + " #####  ######    ####### #     # #     # ######   #####    #     #   #     # #     #   #\n"
                + "#             #   #     # #     # #     # #   #         #   #######   #     # #######   #\n"
                + "#       #     #   #     # #     # #     # #    #  #     #   #     #   #     # #     #   #\n"
                + "#######  #####    #     # #######  #####  #     #  #####    #     #   ######  #     #   #";

        System.out.println(hi);
        System.out.println("=========================================================================================\n"
                + "Welcome to 25 Hours A Day Task Manager!\n"
                + "What can I do for you?\n"
                + "Enter 'help' for the list of commands.\n"
                + "=========================================================================================");

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
            System.out.println("................................. "
                    + "25HoursADay Chat Box ^^ ...............................");
        } else {
            System.out.println("...................................................."
                    + ".....................................");
        }
    }

    /**
     *  Prints when user changes the content of the file.
     */
    public static void printWrongStorageInput() {
        System.out.println("The content of the file is changed by user, cannot load");
    }

    /**
     * Shows the task deleted and the number of tasks left in the list.
     *
     * @param numberDelete task number of the task to be deleted.
     * @param calendarList task list of the task to be deleted.
     */
    public static void printDeleteMessage(int numberDelete, CalendarList calendarList) {
        assert calendarList != null;
        System.out.println("Deleted:\n" + calendarList.getCalendarList().get(numberDelete));
    }

    /**
     * Prints the last additional information of a particular event.
     *
     * @param event event containing the additional information.
     */
    public static void printLastAdditionalInformation(Event event) {
        assert event != null;
        System.out.println("Event: " + event);
        int lastIndexOfAdditionalInformation =
                event.getAdditionalInformationCount() - 1; // -1 to cater for array list starting from 0
        System.out.println("Additional info added: "
                + event.getAdditionalInformationElement(lastIndexOfAdditionalInformation));
    }

    /**
     * Prints the additional information based on the index.
     *
     * @param event     containing the additional information.
     * @param indexInfo index of the additional information in the array list.
     */
    public static void printAdditionalInformation(Event event, int indexInfo) {
        System.out.println("Event: " + event);
        System.out.println("Additional info deleted: "
                + event.getAdditionalInformationElement(indexInfo));
    }

    /**
     * Prints the list of additional information of a particular event.
     *
     * @param additionalInformation array list of the additional information.
     * @param event                 event that contains the additional information.
     */
    public static void printAllAdditionalInformation(ArrayList<String> additionalInformation, Event event) {
        assert event != null;
        int i = 0;
        System.out.println("Event:" + event);

        for (String s : additionalInformation) {
            i++;
            System.out.println(i + ". " + s);
        }
    }

    /**
     * Shows the user the list of items in the calendar list,
     * formatted as an indexed list starting from 1.
     *
     * @param calendarList tasks retrieved from this task list.
     */
    public static void printTaskListView(CalendarList calendarList) {
        assert calendarList != null;
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
        assert calendarList != null;
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
        assert calendarList != null;
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
        assert calendarList != null;
        String calendarItem;
        if (isTask) {
            calendarItem = "task";
        } else {
            calendarItem = "event";
        }
        System.out.println("Got it. I've added this " + calendarItem + ":");

        /* - 1 is catered for array list's index starting from 0. */
        int lastCalendarItemIndex = calendarList.getCalendarList().size() - 1;
        /* condition checker; only Lecture, Lab and Tutorial will print the recurring description*/
        if (calendarList.getCalendarList().get(lastCalendarItemIndex) instanceof Lecture) {
            System.out.println(calendarList.getCalendarList().get(lastCalendarItemIndex).getRecurringDescription());
        } else if (calendarList.getCalendarList().get(lastCalendarItemIndex) instanceof Tutorial) {
            System.out.println(calendarList.getCalendarList().get(lastCalendarItemIndex).getRecurringDescription());
        } else if (calendarList.getCalendarList().get(lastCalendarItemIndex) instanceof Lab) {
            System.out.println(calendarList.getCalendarList().get(lastCalendarItemIndex).getRecurringDescription());
        } else {
            System.out.println(calendarList.getCalendarList().get(lastCalendarItemIndex));
        }
    }

    /**
     * Prints each item's countdown.
     *
     * @param days how many days left.
     * @param item the item to print the countdown.
     */
    public static void printCountDownItem(int days, CalendarItem item) {
        assert item != null;
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
     * @param type         0 is for exam events, 1 is for deadline tasks.
     */
    public static void printCountDownMessage(CalendarList calendarList, int type) {
        switch (type) {
        case 0:
            System.out.println("Here is your exams countdown:");
            for (int i = 0; i < calendarList.getTotalItems(); i++) {
                CalendarItem temp = calendarList.getItem(i);
                Ui.printCountDownItem(((Exam) temp).getCountdown(), temp);
            }
            break;
        case 1:
            System.out.println("Here is your deadlines countdown:");
            for (int i = 0; i < calendarList.getTotalItems(); i++) {
                CalendarItem temp = calendarList.getItem(i);
                Ui.printCountDownItem(((Deadline) temp).getCountdown(), temp);
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
     * Shows the user's progress on deadlines and todos.
     *
     * @param numTotal    integer of number of total user tasks.
     * @param numFinished integer of number of finished tasks.
     */
    public static void printProgress(int numTotal, int numFinished) {
        if (numTotal == 0) {
            System.out.println("You have no deadlines or todos now!");
        } else {
            float progress = (float) numFinished / numTotal * 100;
            System.out.println("You have finished " + numFinished + "/" + numTotal
                    + " (" + String.format("%.2f", progress) + "%) deadlines and todos.");
        }
    }

    /**
     * Print the message after marking a task as important.
     *
     * @param calendarList  the list of user's tasks and events.
     * @param calendarIndex the index of the task in the list.
     */
    public static void printPrioritizeMessage(CalendarList calendarList, int calendarIndex) {
        assert calendarList != null;
        assert calendarIndex >= 0;
        System.out.println(
                "I've marked this task as important:\n"
                        + calendarList.getCalendarList().get(calendarIndex));
    }

    /**
     * Print all important tasks in the list.
     *
     * @param calendarList the list of user's tasks and events.
     */
    public static void printImportantTasks(CalendarList calendarList) {
        int taskCount = 0;
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (!(item instanceof Task)) {
                continue;
            }
            if (((Task) item).getIsImportant()) {
                taskCount++;
                System.out.println(taskCount + ". " + item.toString());
            }
        }
        if (taskCount == 0) {
            System.out.println("You have no important tasks now!");
        } else {
            System.out.println("There are in total " + taskCount + " important tasks in your list.");
        }
    }

    /**
     * Show the earliest important deadline task if exists,
     * and the earliest ordinary deadline task if it exists and is before the important earliest.
     * Show the fist important todo task in the list if exists,
     * otherwise show the first ordinary todo task in the list if exists.
     *
     * @param earliestDeadline     the deadline task with earliest due date.
     * @param earImportantDeadline the important deadline task with earliest due date.
     * @param firstTodo            the first todo task in the list.
     * @param firImportantTodo     the first important todo task in the list.
     */
    public static void printSuggestion(Task earliestDeadline, Task earImportantDeadline,
                                       Task firstTodo, Task firImportantTodo) {
        if (earImportantDeadline == null && earliestDeadline == null
                && firImportantTodo == null && firstTodo == null) {
            System.out.println("You have no unfinished tasks now!");
        } else {
            System.out.println("Maybe you can prepare for the following tasks now:");
            if (earImportantDeadline != null) {
                System.out.println("The earliest unfinished important deadline: "
                        + earImportantDeadline.toString());
                if (earliestDeadline != null
                        && earliestDeadline.getDate().isBefore(earImportantDeadline.getDate())) {
                    System.out.println("The earliest unfinished ordinary deadline: "
                            + earliestDeadline.toString());
                }
            } else if (earliestDeadline != null) {
                System.out.println("The earliest unfinished ordinary deadline: "
                        + earliestDeadline.toString());
            }

            if (firImportantTodo != null) {
                System.out.println("The first unfinished important todo task: "
                        + firImportantTodo.toString());
            } else if (firstTodo != null) {
                System.out.println("The first unfinished ordinary todo task: "
                        + firstTodo.toString());
            }
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
            System.out.println("Error: Please key in the lecture in this format: \n"
                    + "lect <module code> @<venue> -r <number of lecture> /ddMMyy HHmm");
            break;
        case "tutorial":
            System.out.println("Error: Please key in the tutorial in this format: \n"
                    + "tut <module code> @<venue> -r <number of tutorial> /ddMMyy HHmm");
            break;
        case "lab":
            System.out.println("Error: Please key in the lab in this format: \n"
                    + "lab <module code> @<venue> /ddMMyy HHmm");
            break;
        case "exam":
            System.out.println("Error: Please key in the exam in this format: \n"
                    + "exam <module code> @<exam venue> /ddMMyy HHmm");
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
        case "prioritize":
            System.out.println("Error: Please key in the command in this format: *t <task number> ");
            break;
        case "keyword not found":
            System.out.println("There are no tasks matching this keyword. Check that you have spelt it correctly.");
            break;
        case "file not found":
            System.out.println("The file can not be found.");
            break;
        case "invalid done number":
            System.out.println("You can only mark a task as done. An event cannot be marked as done.");
            break;
        case "invalid add info":
            System.out.println(
                    "Error: Please key in the additional information in this format: /a <event number> - information");
            break;
        case "invalid view info":
            System.out.println(
                    "Error: To view the additional information of the event: /v <event number>");
            break;
        case "invalid delete info":
            System.out.println(
                    "Error: To delete an additional information of an event: /- <event number> a <information number>");
            break;
        case "invalid info action":
            System.out.println("Please enter a valid additional information index number.");
            break;
        case "invalid module code":
            System.out.println(
                    "Error: invalid module code. The module code cannot be found in NUS module list.\n"
                            + "Please be reminded to key in the exam in this format: \n"
                            + "exam <module code> @<exam venue> /ddMMyy HHmm");
            break;
        case "storage":
            System.out.println("Content in the file is altered, could not read in the file normally");
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
