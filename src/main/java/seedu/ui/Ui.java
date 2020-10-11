package seedu.ui;

import seedu.data.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Ui {
    private static final String LS = System.lineSeparator();
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    private Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void displayAll(TaskList tasks) {
        // Basic adding sequence
        displayTasks(tasks);
    }

    private void displayTasks(TaskList tasks) {
        // Header
        out.println(LS + "Here is your list of tasks:");
        String format = "%-10s%-15s%-15s%-10s%-10s" + LS;
        out.format(format, "Index", "Description", "Date", "Time", "Priority");
        for (int i = 0; i < tasks.size(); i++) {
            out.format(format,
                    i + 1,
                    tasks.get(i).getDescription(),
                    tasks.get(i).getDate(),
                    tasks.get(i).getTime() == null ? "" : tasks.get(i).getTime(),
                    tasks.get(i).getPriority());
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
