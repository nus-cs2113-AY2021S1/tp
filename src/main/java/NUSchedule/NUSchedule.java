package NUSchedule;

import NUSchedule.ui.UI;

public class NUSchedule {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public NUSchedule(String filePath) {
        ui = new UI();
        try {
            storage = new Storage(filePath);
        } catch (CreatingFileException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.printGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                NUSchedule.command.Command c = NUSchedule.Parser.Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new NUSchedule("data/events.txt").run();
    }
}
