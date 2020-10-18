package seedu.ui;

import seedu.commands.CommandResult;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import static seedu.messages.Messages.LS;
import static seedu.messages.Messages.WELCOME_MESSAGE;


public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void displayAll(TaskMap tasks) {
        // Basic adding sequence
        assert tasks != null : "null tasks";
        displayTasks(tasks);
    }

    private void displayTasks(TaskMap tasks) {
        // Header
        String headerFormat = "  | %-10s | %-20s | %-15s | %-10s | %-10s | %-11s |" + LS;
        String contentFormat = "  | %-10s | %-20s | %-15s | %-10s | %-10s | %-20s |" + LS;
        out.println("   " + padString('_', 93));
        out.format(headerFormat, "Index", "Description", "Date", "Start", "End", "Priority");
        out.println("   " + padString('-', 93));
        for (Task task : tasks.getValues()) {
            out.format(contentFormat,
                "#" + task.getTaskID(),
                limitString(task.getDescription(), 20),
                task.getDate(),
                task.getStartTime() == null ? "" : task.getStartTime(),
                task.getEndTime() == null ? "" : task.getEndTime(),
                task.getPriority());
        }
        out.println("   " + padString('-', 93));
        out.println();
    }

    public void showWelcomeMessage(TaskMap tasks) {
        showMessage(WELCOME_MESSAGE);
        if (tasks != null) {
            showReminders(tasks);
        }
    }

    public void showReminders(TaskMap tasks) {
        assert tasks != null : "null tasks";
        TaskMap tasksDueToday = tasks.searchByDate(LocalDate.now());
        String messageFormat = "%-15s%-30s%15s" + LS;
        String taskFormat = "%-15s%-6s%-18s%-6s%15s" + LS;
        out.println("||" + padString(' ', 56) + "||");
        out.format(messageFormat, "||", "You have " + tasksDueToday.size() + " tasks due today.", "||");
        for (Task task : tasksDueToday.getValues()) {
            out.format(taskFormat,
                "||",
                "#" + task.getTaskID() + " ",
                limitString(task.getDescription(), 17),
                (task.getStartTime() == null ? "" : task.getStartTime()),
                "||");
        }
        out.println("||" + padString(' ', 56) + "||");

        TaskMap tasksDueTomorrow = tasks.searchByDate(LocalDate.now().plusDays(1));
        out.format(messageFormat, "||", "Upcoming tasks tomorrow:", "||");
        for (Task task : tasksDueTomorrow.getValues()) {
            out.format(taskFormat,
                "||",
                "#" + task.getTaskID() + " ",
                limitString(task.getDescription(), 17),
                (task.getStartTime() == null ? "" : task.getStartTime()),
                "||");
        }
        out.println("||" + padString(' ',56) + "||" + LS
                + " " + padString('=', 58) + " " + LS);
    }

    public String limitString(String string, int limit) {
        // TODO Add testing, might need to change to -4 to get an extra space
        return (string.length() > limit) ? (string.substring(0, limit - 3) + "...") : string;
    }

    public String padString(char pad, int length) {
        return String.format("%1$" + length + "s", "").replace(' ', pad);
    }

    public void showMessage(String message) {
        out.println(message);
    }

    public void showException(Exception e) {
        out.println(e);
    }

    public void showCommandResult(CommandResult result) {
        assert result.getMessage() != null : "null message";
        showMessage(result.getMessage());
        if (result.getTasks() != null) {
            displayAll(result.getTasks());
        }
    }
}
