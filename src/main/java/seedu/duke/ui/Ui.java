package seedu.duke.ui;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.card.Topic;
import seedu.duke.card.TopicList;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static Scanner scan = new Scanner(System.in);

    public static String readCommand() {
        return scan.nextLine();
    }

    public static void printStart() {
        String logo = "                               __________\n"
                + "                              |  __ |  _ \\\n"
                + " ____  ______      _____      |  |__| | | |\n"
                + "|  __|/ __ \\ \\    / /| | ____ |   __| | | |\n"
                + "| |  |  __/ \\ \\__/ / | | \\____|  |__| |_| |\n"
                + "| |   \\___|  \\____/  |_| ____/|_____|_____/\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm revisED\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n");
    }

    public static void printSubjectList(List<Subject> list) {
        int num = 1;
        System.out.println("____________________________________________________________\n"
                + "Here are the subject(s) in your list:");
        for (Subject item : list) {
            System.out.println(num + "." + item);
            num++;
        }
        System.out.println("____________________________________________________________");
    }


    public static void printTaskList(Subject subject) {
        int index = 1;
        TaskList taskList = subject.getTasks();
        System.out.println("Here are the tasks(s) under " + subject.getTitle() + ": ");
        for (Task t : taskList.getList()) {
            System.out.println(index + "." + t);
            index++;
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

    public static void printSubjectDelete(Subject subject, int total) {
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this subject:\n   "
                + subject + "\n"
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

    public static void printNoSubjectError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! I'm sorry, but I can't find that subject :-(\n"
                + "____________________________________________________________");
    }

    public static void printRepeatedSubjectError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! I'm sorry, but subject is already in the list :-(\n"
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
                + " ☹ OOPS!!! Invalid index format entered.\n"
                + "____________________________________________________________");
    }

    public static void printOutOfBoundsError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! The index you entered does not exist.\n"
                + "____________________________________________________________");
    }

    public static void printFindTask(TaskList taskList, String find) {
        int taskPresent = 0;
        for (Task task : taskList.getList()) {
            if (task.toString().contains(find)) {
                Ui.printMatch(taskPresent);
                System.out.println(task);
                taskPresent = 1;
            }
        }
        if (taskPresent == 0) {
            System.out.println(" Sorry! I could not find any task with " + find + " in the list.");
        }
        System.out.println("____________________________________________________________");
    }

    public static void printFindSubject(SubjectList subjectList, String find) {
        int taskPresent = 0;
        for (Subject subject : subjectList.getList()) {
            if (subject.toString().contains(find)) {
                Ui.printMatch(taskPresent);
                System.out.println(subject);
                taskPresent = 1;
            }
        }
        if (taskPresent == 0) {
            System.out.println("____________________________________________________________\n"
                    + " Sorry! I could not find any subjects with " + find + " in the list.");
        }
        System.out.println("____________________________________________________________");
    }

    public static void printMatch(int taskPresent) {
        if (taskPresent == 0) {
            System.out.println(" Here are the matching task(s) in your list:");
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

    public static void printGoToSubject(Subject subject) {
        System.out.println("____________________________________________________________\n"
                + "You are currently looking at the subject: " + subject.toString() + "\n"
                + "____________________________________________________________\n");
    }

    public static void printExitToMain() {
        System.out.println("____________________________________________________________\n"
                + "Going back to the main screen.\n"
                + "____________________________________________________________\n");
    }

    public static void printTopicList(Subject subject) {
        int index = 1;
        TopicList topicList = subject.getTopics();
        System.out.println("____________________________________________________________\n"
                + "Here are the topic(s) under " + subject.getTitle() + ": ");
        for (Topic t : topicList.getList()) {
            System.out.println(index + "." + t);
            index++;
        }
        System.out.println();
    }

    public static void printTopicMatch(int taskPresent) {
        if (taskPresent == 0) {
            System.out.println("____________________________________________________________\n"
                    + " Here are the matching topic(s) in your list:");
        }
    }

    public static void printFindTopic(TopicList topicList, String query) {
        int taskPresent = 0;
        for (Topic topic : topicList.getList()) {
            if (topic.toString().contains(query)) {
                Ui.printTopicMatch(taskPresent);
                System.out.println(topic);
                taskPresent = 1;
            }
        }
        if (taskPresent == 0) {
            System.out.println("____________________________________________________________\n"
                    + " Sorry! I could not find any topics with " + query + " in the list.");
        }
        System.out.println();
    }

    public static void printGoToTopic(Topic topic) {
        System.out.println("____________________________________________________________\n"
                + "You are currently looking at the topic: " + topic.getTitle() + "\n"
                + "____________________________________________________________\n");
    }

    public static void printNoTopicError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! I'm sorry, but I can't find that topic :-(\n"
                + "____________________________________________________________");
    }

    public static void printRepeatedTopicError() {
        System.out.println("____________________________________________________________\n"
                + " ☹ OOPS!!! I'm sorry, but that topic is already in the list :-(\n"
                + "____________________________________________________________");
    }

    public static void printTopicDelete(Topic topic, int total) {
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this topic:\n   "
                + topic + "\n"
                + " Now you have " + total + (total == 1 ? " task in the list.\n" : " tasks in the list.\n")
                + "____________________________________________________________");
    }
}
