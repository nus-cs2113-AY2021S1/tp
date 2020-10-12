package seedu.ui;

import seedu.commands.CommandResult;
import seedu.data.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static seedu.messages.Messages.LS;
import static seedu.messages.Messages.WELCOME_MESSAGE;


public class Ui {
    private final Scanner in;
    private final PrintStream out;

    //    public Ui() {
    //        this(System.in, System.out);
    //    }

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        //        // Set default printing color
        //        out.print(DEFAULT_STRING_COLOR);
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
        showMessage(result.getMessage());
        if (result.getTasks() != null) {
            displayAll(result.getTasks());
        }
    }
}
