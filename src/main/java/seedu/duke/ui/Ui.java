package seedu.duke.ui;

import seedu.duke.project.Project;
import seedu.duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final Scanner IN = new Scanner(System.in);
    private static final String LOGO = "   _____  _____ _____  _    _ __  __       _   _  \n"
            + "  / ____|/ ____|  __ \\| |  | |  \\/  |     | | (_) \n"
            + " | (___ | |    | |__) | |  | | \\  / |_ __ | |_ _  ___  _   _ ___\n"
            + "  \\___ \\| |    |  _  /| |  | | |\\/| | '_ \\| __| |/ _ \\| | | / __|\n"
            + "  ____) | |____| | \\ \\| |__| | |  | | |_) | |_| | (_) | |_| \\__ \\ \n"
            + " |_____/ \\_____|_|  \\_\\\\____/|_|  |_| .__/ \\__|_|\\___/ \\__,_|___/ \n"
            + "                                    | |\n"
            + "                                    |_|\n";

    public static void displayTaskDone(Task task, int id) {
        System.out.println("The following task has been marked as done: ");
        System.out.println("\t" + id + ": " + task.getTitle());
    }

    public void printLogo() {
        System.out.println("Hello from\n" + LOGO);
    }

    public String readLine() {
        return IN.nextLine().strip();
    }

    private void printGreeting() {
        System.out.println("Please enter the project details for instantiation.");
    }

    public static void printError(String s) {
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
        System.out.println("The details of the task is as follows:");
        System.out.println("Task title: " + task.getTitle());
        System.out.println("Task Description: " + task.getDescription());
        System.out.println("Task Priority: " + task.getPriority());
        System.out.println("Is it done: " + task.getDone());
    }

    public void invalidCommand() {
        System.out.println("Unknown format or command.");
    }

    public static void displayInvalidId() {
        System.out.println("The following task ID doesn't exist in backlog.\nPlease enter a valid ID.");
    }

    public void printTaskRemoved(Task task) {
        //System.out.println("The corresponding task " + task.toString() + " has been removed.");
        System.out.println("The corresponding task " + task.getTitle() + "has been removed.");
    }

    public void printTaskAdded(Task task) {
        System.out.println("The task " + task.getTitle() + "has been added.");
    }

    public void printPriorityChanged(Task task) {
        System.out.println("The task " + task.getTitle() + "has its priority changed to:");
        System.out.println("\t" + task.getPriority());
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
