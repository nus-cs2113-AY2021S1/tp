package seedu.duke;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class Duke {
    private static ModuleList t;

    public static void main(String[] args) {
        Ui.printWelcomeScreen();
        Ui.printNamePrompt();
        run(t);
    }

    public static void run(ModuleList t) {
        boolean isExit = false;
        while (!isExit) {
            String input = Ui.readCommand();
            Parser.parse(input, ModuleList.modList);
            isExit = Parser.isExit();
        }
    }
}
