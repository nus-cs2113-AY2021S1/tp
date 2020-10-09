package seedu.duke.ui;

import seedu.duke.project.Project;
import seedu.duke.task.Task;

import java.util.Scanner;

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

    public void displayMembers(Project proj) {
        if (proj.members.size() == 0) {
            System.out.println("Currently no members added to the project.");
        } else {
            System.out.println("Here are the members added to you project:");
            for (int i = 0; i < proj.members.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + proj.members.getMember(i).getUserId());
            }
        }
    }

    public void displayProjectBacklog(Project proj) {
        if (proj.backlog.size() == 0) {
            System.out.println("No tasks currently added to project backlog.");
        } else {
            System.out.println("Current tasks in your project backlog");
            for (int i = 0; i < proj.backlog.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + proj.backlog.getTask(i).getTitle());
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

    public void removeMember(String s) {
        System.out.println();
    }

    public void printAddMember(String s) {
        System.out.println("The user associated with " + s + " has been added");
    }

    public void memberNotFound(String s) {
        System.out.println("This member is not associated with this project: " + s);
    }

    public void printMemberAlreadyAdded(String s) {
        System.out.println("The user associated with " + s + " is already added to the project");
    }

    public void printProjectAdded() {
        System.out.println("Project successfully created.");
    }
}
