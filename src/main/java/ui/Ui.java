package ui;

import seedu.duke.Project;
import seedu.duke.Task;

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

    public void welcomeUser(){
        printLogo();
        printGreeting();
    }

    public void displayMembers() {
        if (Project.member.size() == 0) {
            System.out.println("Currently no members added to the project.");
        } else {
            System.out.println("Here are the members added to you project:");
            for (int i = 0; i < Project.member.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + Project.member.get(i).getUserId());
            }
        }
    }

    public void displayTask(Task task) {
        System.out.println(task.toString());
    }
}
