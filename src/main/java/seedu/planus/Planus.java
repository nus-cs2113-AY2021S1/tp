package seedu.planus;

import seedu.commands.Bye;
import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.TaskList;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.UnknowCommandException;
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.task.Task;
import seedu.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.HELP_MESSAGE;

public class Planus {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_CLEAR = "clear";
    // Default date: day that the task is created, default priority: 0 (low to high: 0 - 4)
    private static final Pattern TASK_PATTERN = Pattern.compile(
            "^(?<description>(\\w+\\s*)+\\w+)"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( t/(?<time>\\d{4}))?"
                    + "( p/(?<priority>\\d))?$");
    private static final Pattern EDIT_TASK_PATTERN = Pattern.compile(
            "^(?<index>\\d+)"
                    + "( des/(?<description>(\\w+\\s*)+\\w*))?"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( t/(?<time>\\d{4}))?"
                    + "( p/(?<priority>\\d))?$");
    private final TaskList tasks = new TaskList();
    private boolean isExit;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        new Planus().run();
    }

    private void run() {
        initProgram();
        ui.showWelcomeMessage();
        while (!isExit) {
            String userInput = ui.getUserInput();
            try {
                Command command = parser.processRaw(userInput);
                CommandResult result = command.execute(tasks);
                ui.showCommandResult(result);
                if (command instanceof Bye) {
                    isExit = true;
                    storage.writeTasksToFile(tasks);
                }
            } catch (InvalidCommandException | InvalidPriorityException | UnknowCommandException e) {
                ui.showException(e);
            }
        }
    }

    private void initProgram() {
        storage = new Storage();
        storage.loadTasks(tasks);
        isExit = false;
        ui = new Ui();
        parser = new Parser();
    }

    private void executeCommand(String userInput) throws InvalidPriorityException {
        String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
        String commandType = commandTypeAndParams[0];
        String commandArgs;

        switch (commandType) {
        case COMMAND_HELP:
            ui.showMessage(HELP_MESSAGE);
            break;
        case COMMAND_ADD:
            commandArgs = commandTypeAndParams[1];
            executeAddTask(commandArgs);
            break;
        case COMMAND_LIST:
            ui.displayAll(tasks);
            break;
        case COMMAND_BYE:
            exitProgram();
            break;
        case COMMAND_EDIT:
            commandArgs = commandTypeAndParams[1];
            editTask(commandArgs, tasks);
            break;
        case COMMAND_CLEAR:
            clearTasks(tasks);
            break;
        default:
            System.out.println("Invalid command");
            break;
        }
    }

    private String[] splitCommandWordAndArgs(String userInput) {
        return userInput.split(" ", 2);
    }

    private void executeAddTask(String commandArgs) throws InvalidPriorityException {
        Matcher matcher = TASK_PATTERN.matcher(commandArgs);
        Task task;
        if (matcher.find()) {
            String description = matcher.group("description");
            String dateString = matcher.group("date");
            String timeString = matcher.group("time");
            String priorityString = matcher.group("priority");
            task = new Task(description, dateString, timeString, priorityString);
        } else {
            // TODO throw new InvalidCommandException();
            System.out.println("Invalid command!");
            return;
        }
        tasks.addTask(task);
        System.out.println("\nTask added:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in your list.\n");
    }

    private void exitProgram() {
        isExit = true;
        storage.writeTasksToFile(tasks);
        System.out.println("\nBye! See you again!");
    }

    private void editTask(String commandArgs, TaskList tasks) throws InvalidPriorityException {
        Matcher matcher = EDIT_TASK_PATTERN.matcher(commandArgs);
        if (matcher.find()) {
            String indexString = matcher.group("index");
            String description = matcher.group("description");
            String dateString = matcher.group("date");
            String timeString = matcher.group("time");

            int index = Integer.parseInt(indexString) - 1;
            if (description != null) {
                tasks.get(index).setDescription(description);
            }
            if (dateString != null) {
                tasks.get(index).setDate(dateString);
            }
            if (timeString != null) {
                tasks.get(index).setTime(timeString);
            }
            String priorityString = matcher.group("priority");
            if (priorityString != null) {
                tasks.get(index).setPriority(priorityString);
            }
            System.out.println("\nTask edited:");
            System.out.println(indexString + ". " + tasks.get(index).toString() + "\n");
        } else {
            // TODO throw new InvalidCommandException();
            System.out.println("Invalid command!");
        }
    }

    private void clearTasks(TaskList tasks) {
        tasks.clear();
        System.out.println("\nAll tasks cleared.\n");
    }
}
