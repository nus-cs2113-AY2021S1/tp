package seedu.duke;

import seedu.duke.parser.Parser;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    static Ui ui = new Ui();

    public static void main(String[] args) {
        ui.printLogo();
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        ArrayList<Project> projectList = new ArrayList<>(10);

        System.out.print("Hello " + in.nextLine());
        while (true) {
            new Parser().parser(ui, projectList);
        }
    }
}