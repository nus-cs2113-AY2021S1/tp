package seedu.planus;

import seedu.commands.Bye;
import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.TaskList; 
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.ui.Ui;


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
        ui.showNotifications(tasks);
        while (!isExit) {
            String userInput = ui.getUserInput();
            assert userInput != null : "null input";
            try {
                Command command = parser.processRaw(userInput);
                assert command != null : "null command";
                CommandResult result = command.execute(tasks);
                assert result != null : "null command result";
                ui.showCommandResult(result);
                if (command instanceof Bye) {
                    isExit = true;
                    storage.writeTasksToFile(tasks);
                }
            } catch (Exception e) {
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
