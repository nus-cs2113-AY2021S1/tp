package seedu.modtracker;

/**
 * Main entry-point for the java.seedu.modtracker application.
 */
public class ModTracker {
    private Ui ui;
    private ModuleList modList;
    private Storage storage;
    private TaskList taskList;
    private Notification notification;

    public static void main(String[] args) {
        new ModTracker("data/modtracker.txt").run();
    }

    public ModTracker(String filePath) {
        ui = new Ui();
        modList = new ModuleList();
        storage = new Storage(filePath);
        taskList = new TaskList();
        notification = new Notification();
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.printWelcomeScreen();
        String name = storage.getName();
        if (name == null) {
            name = ui.printNamePrompt();
            storage.appendToFile(name);
        }
        ui.printGreeting(name);
        storage.loadData(this);
        storage.unlockFile();
        notification.start(modList);
        runCommandLoopUntilExitCommand(name);
        storage.lockFile();
    }

    /**
     * Reads the user command until the user enters the exit command and terminates the program.
     *
     * @param name name of user
     */
    private void runCommandLoopUntilExitCommand(String name) {
        Parser parser = new Parser();
        while (!parser.isExit()) {
            String input = ui.readCommand();
            parser.parse(input, modList, name, storage, true, taskList);
        }
    }

    public ModuleList getModList() {
        return modList;
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

}
