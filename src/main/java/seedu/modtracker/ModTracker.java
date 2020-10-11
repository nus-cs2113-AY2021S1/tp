package seedu.modtracker;

/**
 * Main entry-point for the java.seedu.modtracker application.
 */
public class ModTracker {
    private Storage storage;
    private Ui ui = new Ui();
    private ModuleList modList = new ModuleList();

    public static void main(String[] args) {
        new ModTracker().run();
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        String name = start();
        storage = new Storage("data/modtracker.txt");
        runCommandLoopUntilExitCommand(name);
    }

    /**
     * Starts the program, prints the welcome message and name-enter prompt.
     *
     * @return name of user
     */
    public String start() {
        ui.printWelcomeScreen();
        return ui.printNamePrompt();
    }

    /**
     * Reads the user command until the user enters the exit command and terminates the program.
     *
     * @param name name of user
     */
    public void runCommandLoopUntilExitCommand(String name) {
        Parser parser = new Parser();
        while (!parser.isExit()) {
            String input = ui.readCommand();
            parser.parse(input, modList, name, storage);
        }
    }

}
