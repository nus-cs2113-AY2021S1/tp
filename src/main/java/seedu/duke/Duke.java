package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.parser.Parser;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static Parser parser = new Parser();
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
    }

    /**
     * Welcome the user and initialise the local storage.
     */
    private void init() {
        Ui.showWelcomeScreen();
    }

    /**
     * Get the user input and runs the instructions.
     * Exits when ExitCommand returns "true"
     */
    private static void runInstructions() {
        String input;
        while (true) {
            input = Ui.getUserCommand();
            parser.parser(input, projectList);
        }
    }
}