package seedu.duke;

import seedu.duke.parser.Parser;
import seedu.duke.project.Project;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static final String dataFilename = "data.json";

    private static Parser parser = new Parser();
    private static StorageManager sm;
    private static ArrayList<Project> projectList = new ArrayList<>(10);

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
        sm = new StorageManager(dataFilename, projectList);
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
            String parserOutput = parser.parser(input, projectList);
            if (parserOutput != null) {
                System.out.println(parserOutput);
            }
        }
    }
}