package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.parser.Parser;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static java.lang.System.exit;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static final String dataFilename = "data.json";

    private static Parser parser = new Parser();
    private static StorageManager sm;
    private static ProjectManager projectManager = new ProjectManager();

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the program.
     */
    private void run() {
        init();
        runInstructions();
        destroy();
    }

    /**
     * Welcome the user and initialise the local storage.
     */
    private void init() {
        sm = new StorageManager(dataFilename, projectManager);
        try {
            sm.load();
        } catch (IOException e) {
            exit(1);
        }
        Ui.showWelcomeScreen();
    }

    private void destroy() {
        sm.save();
    }

    /**
     * Get the user input and runs the instructions.
     * Exits when ExitCommand returns "true"
     */
    private static void runInstructions() {
        String input;
        while (!parser.isExit()) {
            input = Ui.getUserCommand();
            Command command = parser.parser(input, projectManager);
            if (command != null) {
                command.execute();
            }
        }
    }
}