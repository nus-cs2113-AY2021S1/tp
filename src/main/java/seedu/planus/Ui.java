package seedu.planus;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private ArrayList<Task> tasks;

    private Month month = LocalDate.now().getMonth();

    public Ui(ArrayList<Task> tasks) {
        this(System.in, System.out, tasks);
    }

    private Ui(InputStream in, PrintStream out, ArrayList<Task> tasks) {
        this.in = new Scanner(in);
        this.out = out;
        this.tasks = tasks;
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void displayAll() {
        // Basic adding sequence
        displayTasks(tasks);
    }

    public void displayAllByTime() {
        // Sort by datetime, default display mode
        List<Task> sorted = tasks.stream()
                .sorted(new DateSorter())
                .collect(Collectors.toList());
        displayTasks((ArrayList<Task>) sorted);
    }

    public void displayAllByPriority() {
        // Sort by priority, same priority then datetime
        List<Task> sorted = tasks.stream()
                .sorted(new DateSorter())
                .sorted(new PrioritySorter())
                .collect(Collectors.toList());
        displayTasks((ArrayList<Task>) sorted);
    }

    private void displayTasks(ArrayList<Task> tasks) {
        out.println("\nHere is your list of tasks:");
        for (Task task : tasks) {
            out.println((tasks.indexOf(task) + 1) + ". " + task);
        }
        out.println();
    }

    public void showWelcomeMessage() {
        out.println("\nWelcome to\n"
                + "    ____  __      _   ____  _______\n"
                + "   / __ \\/ /___ _/ | / / / / / ___/\n"
                + "  / /_/ / / __ `/  |/ / / / /\\__ \\ \n"
                + " / ____/ / /_/ / /|  / /_/ /___/ / \n"
                + "/_/   /_/\\__,_/_/ |_/\\____//____/  "
                + "v1.0\n");
    }

    public void showCommands() {
        System.out.println("\nList of available commands:");
        System.out.println("- help: show list of available commands");
        System.out.println("- add: add a task");
        System.out.println("- list: show list of tasks");
        System.out.println("- bye: exit the program\n");
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

}
