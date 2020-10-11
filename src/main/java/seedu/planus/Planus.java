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

public class Planus {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

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
}
