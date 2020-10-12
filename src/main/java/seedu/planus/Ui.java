package seedu.planus;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ui {
    private static final String LS = System.lineSeparator();
    private final Scanner in;
    private final PrintStream out;
    private ArrayList<Task> tasks;

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
        // Header
        out.println(LS + "Here is your list of tasks:");
        String format = "%-10s%-15s%-15s%-10s%-10s" + LS;
        out.format(format, "Index", "Description", "Date", "Time", "Priority");
        for (Task task : tasks) {
            out.format(format,
                    tasks.indexOf(task) + 1,
                    task.getDescription(),
                    task.getDate(),
                    task.getTime() == null ? "" : task.getTime(),
                    task.getPriority());
        }
        out.println();
    }

    public void showWelcomeMessage() {
        out.println(LS + "Welcome to" + LS
                + "    ____  __      _   ____  _______" + LS
                + "   / __ \\/ /___ _/ | / / / / / ___/" + LS
                + "  / /_/ / / __ `/  |/ / / / /\\__ \\ " + LS
                + " / ____/ / /_/ / /|  / /_/ /___/ / " + LS
                + "/_/   /_/\\__,_/_/ |_/\\____//____/  "
                + "v1.0" + LS);
    }

    public void showCommands() {
        out.println(LS + "List of available commands:");
        out.println("- help: show list of available commands");
        out.println("- add: add a task");
        out.println("- list: show list of tasks");
        out.println("- bye: exit the program" + LS);
    }

}
