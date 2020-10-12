package seedu.duke;

import seedu.duke.parser.Parser;
import seedu.duke.project.Project;
import seedu.duke.ui.old.Ui;

import java.util.ArrayList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    static Ui ui = new Ui();
    private static Parser parser = new Parser();

    public static void main(String[] args) {
        ArrayList<Project> projectList = new ArrayList<>(10);
        ui.welcomeUser();
        while (true) {
            new Parser().parser(ui, projectList);
        }
    }
}