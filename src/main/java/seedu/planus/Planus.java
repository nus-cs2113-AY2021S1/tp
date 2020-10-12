package seedu.planus;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Planus {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_SEARCH = "search";
    // Default date: day that the task is created, default priority: 0 (low to high: 0 - 4)
    private static final Pattern TASK_PATTERN = Pattern.compile(
            "^(?<description>(\\w+\\s*)+\\w*)"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( t/(?<time>\\d{4}))?"
                    + "( p/(?<priority>\\d))?$");
    private static int isWorking = 1;
    private final ArrayList<Task> tasks = new ArrayList<>();
    private boolean isExit;
    private Storage storage;
    private Ui ui;

    public static void main(String[] args) {
        new Planus().run();
    }

    private void run() {
        initProgram();
        ui.showWelcomeMessage();
        while (!isExit) {
            String userInput = ui.getUserInput();
            executeCommand(userInput);
        }
    }

    private void initProgram() {
        storage = new Storage();
        storage.loadTasks(tasks);
        isExit = false;
        ui = new Ui(tasks);
    }

    private void executeCommand(String userInput) {
        String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
        String commandType = commandTypeAndParams[0];

        switch (commandType) {
            case COMMAND_HELP:
                ui.showCommands();
                break;
            case COMMAND_ADD:
                executeAddTask(commandTypeAndParams);
            case COMMAND_LIST:
                ui.displayAll();
                break;
            case COMMAND_SEARCH:
                executeSearchTask(commandTypeAndParams);
                break;
            case COMMAND_BYE:
                exitProgram();
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
    }

    private String[] splitCommandWordAndArgs(String userInput) {
        return userInput.split(" ", 2);
    }

    private void executeAddTask(String[] commandTypeAndParams) {
        try {
            String commandArgs = commandTypeAndParams[1];
            Matcher matcher = TASK_PATTERN.matcher(commandArgs);
            Task task;
            if (matcher.find()) {
                String description = matcher.group("description");
                String dateString = matcher.group("date");
                String timeString = matcher.group("time");
                String priorityString = matcher.group("priority");
                task = new Task(description, dateString, timeString, priorityString);
                isWorking = 1;
            } else {
                // TODO throw new InvalidCommandException();
                System.out.println("Invalid command!");
                isWorking = 0;
                return;
            }
            if (isWorking == 1) {
                tasks.add(task);
                System.out.println("\nTask added:");
                System.out.println(task.toString());
                System.out.println("Now you have " + tasks.size() + " task(s) in your list.\n");
            } else {
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Command description cannot be empty!");
        }
    }

    private void executeSearchTask(String[] commandTypeAndParams) {
        try {
            final ArrayList<Task> searchTasks = new ArrayList<>();
            String toSearch = commandTypeAndParams[1];
            for(int i = 0; i < tasks.size(); i++)
            {
                if(tasks.get(i).getDescription().contains(toSearch)){
                    searchTasks.add(tasks.get(i));
                }
            }
            String LS = System.lineSeparator();
            System.out.println(LS + "Here is your list of tasks which contain the word/letters "+toSearch+" :");
            String format = "%-10s%-15s%-15s%-10s%-10s" + LS;
            System.out.format(format, "Index", "Description", "Date", "Time", "Priority");
            for (Task task : searchTasks) {
                System.out.format(format,
                        tasks.indexOf(task) + 1,
                        task.getDescription(),
                        task.getDate(),
                        task.getTime() == null ? "" : task.getTime(),
                        task.getPriority());
            }
            System.out.println();
            }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Command description cannot be empty!");
        }
    }

    private void exitProgram() {
        isExit = true;
        storage.writeTasksToFile(tasks);
        System.out.println("\nBye! See you again!");
    }
}
