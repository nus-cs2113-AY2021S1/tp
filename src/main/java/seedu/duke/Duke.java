package seedu.duke;

import seedu.duke.controller.Controller;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.runApp();
    }

}
