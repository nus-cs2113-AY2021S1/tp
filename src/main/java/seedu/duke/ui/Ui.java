package seedu.duke.ui;

import seedu.duke.task.Task;

import java.util.Scanner;

import static seedu.duke.project.Project.backlog;
import static seedu.duke.project.Project.member;


public class Ui {
    private static final Scanner IN = new Scanner(System.in);
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private void printLogo() {
        System.out.println("Hello from\n" + LOGO);
    }

    public String readLine() {
        return IN.nextLine().strip();
    }

    private void printGreeting() {
        System.out.println("Please enter the project details for instantiation");
    }

    public void printError(String s) {
        System.out.println(s);
    }

    public void welcomeUser() {
        printLogo();
        printGreeting();
    }

    public void displayMembers() {
        if (member.size() == 0) {
            System.out.println("Currently no members added to the project.");
        } else {
            System.out.println("Here are the members added to you project:");
            for (int i = 0; i < member.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + member.getMember(i).getUserId());
            }
        }
    }

    public void displayProjectBacklog() {
        if (backlog.size() == 0) {
            System.out.println("No tasks currently added to project backlog.");
        } else {
            System.out.println("Current tasks in your project backlog");
            for (int i = 0; i < backlog.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + backlog.getTask(i).getTitle());
            }
        }
    }

    public void displayTask(Task task) {
        System.out.println(task.toString());
    }

    public void invalidCommand() {
        System.out.println("Unknown format or command.");
    }

    public void displayInvalidId() {
        System.out.println("The following task id doesn't exist in backlog.\n Please enter a valid id.");
    }

    public void printTaskRemoved(Task task) {
        System.out.println("The corresponding task " + task.toString() + " has been removed.");
    }

}
