package seedu.duke;

import seedu.duke.calendar.CalendarList;
import seedu.duke.command.Command;

import java.io.FileNotFoundException;

/**
 * Entry point of the Main application.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    private Storage storage;
    private CalendarList calendarList;
    private Ui ui;

    /**
     * Initializes the application and imports the data stored locally to the application.
     *
     * @param filePath Filepath of the storage data.
     */
    public Main(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        calendarList = new CalendarList();
        try {
            storage.readFromFile(calendarList);
        } catch (DukeException e) {
            Ui.printDukeExceptionMessage(e, calendarList);
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
                Ui.printBorder(true);
                c.execute(calendarList, storage);
                isExit = c.isExit();
            } catch (CommandException e) {
                Ui.printExceptionMessage(e, calendarList);
            } finally {
                Ui.printBorder(false);
            }
        }
    }


    public static void main(String[] args) {
        new Main("data/tasks.txt").run();
    }
}
