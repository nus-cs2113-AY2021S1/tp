package seedu.duke;

import seedu.duke.calendar.CalendarList;
import seedu.duke.command.Command;

import java.io.FileNotFoundException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private CalendarList calendarList;
    private Ui ui;

    /**
     * Initializes the application and imports the data stored locally to the application.
     *
     * @param filePath Filepath of the storage data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        calendarList = new CalendarList();
        try {
            storage.readFromFile(calendarList);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundErrorMessage();
        }
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     * Greets the user upon start up and exit.
     */
    public void run() {
        //the following are all test code
        String code = "CS1010";
        System.out.println("THIS is some testing:::::::\n");
        ModuleChecker moduleChecker = new ModuleChecker();
        System.out.println("This is after creating a Module checker object\n");
        if (moduleChecker.isModuleValid(code)) {
            System.out.println(code + " is in the module list\n");
        } else {
            System.out.println("Nothing is found\n");
        }
        System.out.println("This is after calling the isModuleValid function\n");

        // the above are all testing codes
        //Ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.handleUserInput(fullCommand);
                Ui.printDukeBorder(true);
                c.execute(calendarList, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                Ui.printDukeExceptionMessage(e, calendarList);
            } finally {
                Ui.printDukeBorder(false);
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
