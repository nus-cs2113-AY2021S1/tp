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
    private static final Pattern TASK_PATTERN = Pattern.compile(
            "^(?<description>(\\w+\\s*)+\\w*)"
            + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
            + "( t/(?<time>\\d{4}))?"
            + "( p/(?<priority>\\d))?$");

    private final ArrayList<Task> tasks = new ArrayList<>();
    private boolean isExit;
    private Storage storage;

    public static void main(String[] args) {
        new Planus().run();
    }

    private void run() {
        showWelcomeMessage();
        initProgram();
        while (!isExit) {
            String userInput = getUserInput();
            executeCommand(userInput);
        }
    }

    private void showWelcomeMessage() {
        System.out.println("\nWelcome to\n"
                + "    ____  __      _   ____  _______\n"
                + "   / __ \\/ /___ _/ | / / / / / ___/\n"
                + "  / /_/ / / __ `/  |/ / / / /\\__ \\ \n"
                + " / ____/ / /_/ / /|  / /_/ /___/ / \n"
                + "/_/   /_/\\__,_/_/ |_/\\____//____/  "
                + "v1.0\n");
    }

    private void initProgram() {
        storage = new Storage();
        storage.loadTasks(tasks);
        isExit = false;

        //        Ui ui = new Ui();
        //        ui.printScreen();

        //        Calendar c = Calendar.getInstance();
        //        System.out.println(c.get(Calendar.YEAR));
        //        System.out.println(c.get(Calendar.DAY_OF_MONTH));
        //        System.out.println(c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH));
        //        System.out.println(c);

        //        LocalDate d = LocalDate.now();
        //        System.out.println(d);
        //        int year = d.getYear();
        //        int month = d.getMonthValue();
        //        d = LocalDate.of(year, month, 1);
        //        System.out.println(d);
        //        System.out.println(d.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
    }

    private String getUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private void executeCommand(String userInput) {
        String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
        String commandType = commandTypeAndParams[0];

        switch (commandType) {
        case COMMAND_HELP:
            showCommands();
            break;
        case COMMAND_ADD:
            String commandArgs = commandTypeAndParams[1];
            executeAddTask(commandArgs);
            break;
        case COMMAND_LIST:
            showList();
            break;
        case COMMAND_BYE:
            exitProgram();
            break;
        default:
            System.out.println("Invalid command");
            break;
        }
    }

    private void showCommands() {
        System.out.println("\nList of available commands:");
        System.out.println("- help: show list of available commands");
        System.out.println("- add: add a task");
        System.out.println("- list: show list of tasks");
        System.out.println("- bye: exit the program\n");
    }

    private String[] splitCommandWordAndArgs(String userInput) {
        return userInput.split(" ", 2);
    }

    private void executeAddTask(String commandArgs) {
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
        tasks.add(task);
        System.out.println("\nTask added:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in your list.\n");
    }

    private void showList() {
        System.out.println("\nHere is your list of tasks:");
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + ". " + task.toString());
        }
        System.out.println();
    }


    private void exitProgram() {
        isExit = true;
        storage.writeTasksToFile(tasks);
        System.out.println("\nBye! See you again!");

    }
}
