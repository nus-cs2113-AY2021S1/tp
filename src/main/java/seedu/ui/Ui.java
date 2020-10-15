package seedu.ui;

import seedu.commands.CommandResult;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
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
        String format = "%-10s%-20s%-15s%-10s%-10s%-10s" + LS;
        out.format(format, "Index", "Description", "Date", "Start", "End", "Priority");
        for (Task task : tasks.getValues()) {
            out.format(format,
                task.getTaskID(),
                task.getDescription().length() > 20
                        ? task.getDescription().substring(0, 16) + "..." : task.getDescription(),
                task.getDate(),
                task.getStartTime() == null ? "" : task.getStartTime(),
                task.getEndTime() == null ? "" : task.getEndTime(),
                task.getPriority());
        }
        out.println();
    }

    public void showWelcomeMessage() {
        showMessage(WELCOME_MESSAGE);
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
