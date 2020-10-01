package seedu.duke;

import seedu.duke.Command.Command;
import seedu.duke.Task.TaskList;

import java.io.FileNotFoundException;

/**
 *Entry point of the Duke application.
 *Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initializes the application and imports the data stored locally to the application.
     *
     * @param filePath Filepath of the storage data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();

        try {
            storage.importData(taskList);
        } catch (FileNotFoundException e) {
            Ui.printNoImportDataMessage();
        }
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     * Greets the user upon start up and exit.
     */
    public void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.handleUserInput(fullCommand);
                Ui.printDukeBorder(true);
                c.execute(taskList, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                Ui.printDukeExceptionMessage(e, taskList);
            } finally {
                Ui.printDukeBorder(false);
            }
        }
        Ui.printExitMessage();
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
