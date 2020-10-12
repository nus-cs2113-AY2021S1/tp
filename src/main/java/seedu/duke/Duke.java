package seedu.duke;

import seedu.duke.parser.Parser;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static Parser parser = new Parser();

    public static void main(String[] args) {
        ArrayList<Project> projectList = new ArrayList<>(10);
        Ui.showWelcomeScreen();

        while (true) {
            String userInput = Ui.getUserCommand();
            parser.parser(userInput, projectList);
        }
    }
}