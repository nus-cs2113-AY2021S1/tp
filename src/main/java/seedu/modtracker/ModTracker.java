package seedu.modtracker;

/**
 * Main entry-point for the java.seedu.modtracker application.
 */
public class ModTracker {
    private static ModuleList modules;

    public static void main(String[] args) {
        Ui.printWelcomeScreen();
        String name = Ui.printNamePrompt();
        run(modules, name);
    }

    public static void run(ModuleList modules, String name) {
        while (!Parser.isExit()) {
            String input = Ui.readCommand();
            Parser.parse(input, modules, name);
        }
    }
}
