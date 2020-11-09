import access.Access;
import commands.Command;
import common.KajiLog;
import exception.IncorrectAccessLevelException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import exception.ExclusionFileException;
import exception.StorageDataException;

import manager.admin.Admin;
import parser.Parser;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import static common.Messages.MESSAGE_HELP;


/**
 * Entry point of the Kaji application.
 * Initializes the application and starts the interaction with the user.
 */
public class Kaji {
    private Ui ui;
    private Access access;
    private Storage storage;
    private static final Logger logger = KajiLog.getLogger("Kaji");

    /**
     * Sets up the required objects and loads the data from the storage file.
     *
     * @param filePath filepath of the storage file
     * @throws IOException if there is an error renaming the storage file
     */
    public Kaji(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            Admin admin = new Admin(storage.loadModule());
            access = new Access(admin);
        } catch (FileNotFoundException e) {
            storage.createAdmin();
            access = new Access();
        }
    }

    /** Runs the program until termination. */
    public void run() {
        logger.info("Starting up Kaji...");
        ui.showWelcome();
        ui.showToUser(MESSAGE_HELP);
        ui.printLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showLevel(access);
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, access);
                c.execute(ui, access, storage);
                ui.printLine();
                isExit = c.isExit();
            } catch (InvalidInputException | IncorrectAccessLevelException | IOException
                    | IndexOutOfBoundsException | InvalidFileFormatException | ExclusionFileException
                    | StorageDataException e) {
                logger.warning("An error occured: " + e.getMessage());
                ui.showError(e.getMessage());
                ui.printLine();
            }
        }
        logger.info("Exiting Kaji...");
    }

    public static void main(String[] args) throws IOException {
        new Kaji("data/admin").run();
    }
}
