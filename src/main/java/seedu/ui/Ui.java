package seedu.ui;

import seedu.commands.CommandResult;
import seedu.data.TaskList;

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
        this.in = new Scanner(System.in);
        //        this.out = new PrintStream(System.out, true, StandardCharsets.ISO_8859_1);
        this.out = out;
        // Set default printing color
        //        out.print(DEFAULT_STRING_COLOR);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void displayAll(TaskList tasks) {
        // Basic adding sequence
        assert tasks != null : "null tasks";
        displayTasks(tasks);
    }

    private void displayTasks(TaskList tasks) {
        // Header
        String headerFormat = "  | %-10s | %-20s | %-15s | %-10s | %-10s | %-11s |" + LS;
        String contentFormat = "  | %-10s | %-20s | %-15s | %-10s | %-10s | %-20s |" + LS;
        out.println("   _____________________________________________________________________________________________ ");
        out.format(headerFormat, "Index", "Description", "Date", "Start", "End", "Priority");
        out.println("   --------------------------------------------------------------------------------------------- ");
        for (int i = 0; i < tasks.size(); i++) {
            out.format(contentFormat,
                    i + 1,
                    limitString(tasks.get(i).getDescription(), 20),
                    tasks.get(i).getDate(),
                    tasks.get(i).getStartTime() == null ? "" : tasks.get(i).getStartTime(),
                    tasks.get(i).getEndTime() == null ? "" : tasks.get(i).getEndTime(),
                    tasks.get(i).getPriority());
        }
        out.println("   --------------------------------------------------------------------------------------------- ");
        out.println();
    }

    public void showWelcomeMessage(TaskList tasks) {
        showMessage(WELCOME_MESSAGE);
        showNotifications(tasks);
    }

    public void showNotifications(TaskList tasks) {
        TaskList tasksDueToday = tasks.getTasksDueToday();
        TaskList tasksDueTomorrow = tasks.getTasksDueTomorrow();
        String messageFormat = "%-15s%-30s%15s" + LS;
        String taskFormat = "%-15s%-3s%-18s%-5s%19s" + LS;
        out.println("||                                                        ||");
        out.format(messageFormat, "||", "You have " + tasksDueToday.size() + " tasks due today.", "||");
        for (int i = 0; i < tasksDueToday.size(); i++) {
            out.format(taskFormat,
                    "||",
                    i + 1 + ".",
                    limitString(tasksDueToday.get(i).getDescription(), 17),
                    (tasksDueToday.get(i).getStartTime() == null ? "" : tasksDueToday.get(i).getStartTime()),
                    "||");
        }
        out.println("||                                                        ||");
        out.format(messageFormat, "||", "Upcoming tasks tomorrow:", "||");
        for (int i = 0; i < tasksDueTomorrow.size(); i++) {
            out.format(taskFormat,
                    "||",
                    i + 1 + ".",
                    limitString(tasksDueTomorrow.get(i).getDescription(), 17),
                    (tasksDueTomorrow.get(i).getStartTime() == null ? "" : tasksDueTomorrow.get(i).getStartTime()),
                    "||");
        }
        out.println("||                                                        ||" + LS
                + " ========================================================== " + LS);
    }

    public String limitString(String string, int limit) {
        return (string.length() > limit) ? (string.substring(0, limit - 3) + "...") : string;
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
