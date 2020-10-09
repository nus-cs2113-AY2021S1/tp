package seedu.modtracker;

/**
 * Main entry-point for the java.seedu.modtracker application.
 */
public class ModTracker {
    private Ui ui;
    private Storage storage;
    private final ModuleList modList = new ModuleList();

    public static void main(String[] args) {
        new ModTracker().run();
    }

    public void run() {
        String name = start();
        storage = new Storage("data/modtracker.txt");
        runCommandLoopUntilExitCommand(modList, name);
    }

    public String start() {
        this.ui = new Ui();
        ui.printWelcomeScreen();
        return ui.printNamePrompt();
    }

    public void runCommandLoopUntilExitCommand(ModuleList modules, String name) {
        Parser parser = new Parser();
        while (!parser.isExit()) {
            String input = ui.readCommand();
            parser.parse(input, modList, name, storage);
        }
    }

}
