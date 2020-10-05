package seedu.duke;


import seedu.duke.ui.TextUi;

/**
 * Entry point of the SmartHome application.
 * Initializes the application and starts the interaction with the user.
 */

public class SmartHomeBot {

    private static TextUi ui = new TextUi();

    public static void main(String[] args) {
        new SmartHomeBot().run();
    }

    /** Runs the program until termination.  */
    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Initialise the import of data from the text file,
     * and prints the welcome message.
     *
     */
    private void start() {
        this.ui = new TextUi();
        ui.showWelcomeMessage();
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        ui.showGoodByeMessage();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the bye command.  */
    private void runCommandLoopUntilExitCommand() {
        String userCommandText;
        do {
            userCommandText = ui.getUserCommand();
        } while (!userCommandText.equals("exit"));
    }

}
