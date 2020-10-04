package seedu.duke.ui;

import seedu.duke.task.Task;
import seedu.duke.list.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static Scanner scan = new Scanner(System.in);

    public static String readCommand() {
        return scan.nextLine();
    }

    public static void printStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n");
    }

    public static void printList(List<Task> list) {
        int num = 1;
        System.out.println("____________________________________________________________\n"
                + "Here are the task(s) in your list:");
        for (Task item : list) {
            System.out.println(num + "." + item);
            num++;
        }
        System.out.println("____________________________________________________________");
    }

    public static void printDone(Task task) {
        System.out.println("____________________________________________________________\n"
                +
                " Nice! I've marked this task as done:\n   " + task + "\n"
                + "____________________________________________________________");
    }

    public static void printDelete(Task task, int total) {
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this task:\n   "
                + task + "\n"
                + " Now you have " + total + (total == 1 ? " task in the list.\n" : " tasks in the list.\n")
                + "____________________________________________________________");
    }

    public static void printBye() {
        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________");
    }

    public static void printError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "____________________________________________________________");
    }

    public static void printTodoError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! The description of a todo cannot be empty.\n"
                + "____________________________________________________________");
    }

    public static void printDeadlineError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! The description of a deadline cannot be empty.\n"
                + "____________________________________________________________");
    }

    public static void printEventError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! The description of a event cannot be empty.\n"
                + "____________________________________________________________");
    }

    public static void printIndexError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! Invalid index format entered\n"
                + "____________________________________________________________");
    }

    public static void printFind(TaskList taskList, String find) {
        int taskPresent = 0;
        for (Task task : taskList.getList()) {
            if (task.toString().contains(find)) {
                Ui.printMatch(taskPresent);
                System.out.println(task);
                taskPresent = 1;
            }
        }
        if (taskPresent == 0) {
            System.out.println("____________________________________________________________\n"
                    + " Sorry! I could not find any task with " + find + " in the list");
        }
        System.out.println("____________________________________________________________");
    }

    public static void printMatch(int taskPresent) {
        if (taskPresent == 0) {
            System.out.println("____________________________________________________________\n"
                    + " Here are the matching task(s) in your list:");
        }
    }

    public static void fileNotFoundError() {
        System.out.println("File not found. Creating file...");
    }

    public static void createFileError() {
        System.out.println("Creating file failed.");
    }

    public static void printWritingError() {
        System.out.println("Writing to file failed.");
    }
}
