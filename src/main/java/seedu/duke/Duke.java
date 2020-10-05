package seedu.duke;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class Duke {
    private static ModuleList modules;

    public static void main(String[] args) {
        Ui.printWelcomeScreen();
        Ui.printNamePrompt();
        run(modules);
    }

    public static void run(ModuleList modules) {
        while (!Parser.isExit()) {
            String input = Ui.readCommand();
            Parser.parse(input, modules);
        }
    }
}
