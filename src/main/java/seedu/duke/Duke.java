package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
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
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();

        storage.readFromFile(taskList);
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
    }


    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
