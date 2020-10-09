package seedu.modtracker;

/**
 * Main entry-point for the java.seedu.modtracker application.
 */
public class ModTracker {
    private static ModuleList modules;
    private static Storage storage;

    public static void main(String[] args) {
        Ui.printWelcomeScreen();
        String name = Ui.printNamePrompt();
        storage = new Storage("data/modtracker.txt");
        run(modules, name);
    }

    public static void run(ModuleList modules, String name) {
        while (!Parser.isExit()) {
            String input = Ui.readCommand();
            Parser.parse(input, modules, name, storage);
        }
    }
}
