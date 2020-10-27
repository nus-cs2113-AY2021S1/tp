import access.Access;
import commands.Command;
import common.KajiLog;
import exception.ExclusionFileException;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import exception.InvalidFileFormatException;
import manager.admin.Admin;
import parser.Parser;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import static common.Messages.MESSAGE_HELP;

public class Kaji {
    private Ui ui;
    private Access access;
    private Storage storage;
    private static final Logger logger = KajiLog.getLogger("Kaji");

    public Kaji(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            Admin admin = new Admin(storage.loadModule(ui));
            access = new Access(admin);
        } catch (FileNotFoundException e) {
            storage.createAdmin(ui);
            access = new Access();
        }
    }

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
                     | IndexOutOfBoundsException | InvalidFileFormatException | ExclusionFileException e) {
                logger.warning("An error occured: " + e.getMessage());
                ui.showError(e.getMessage());
                ui.printLine();
            }
        }
        logger.info("Exiting Kaji...");
    }

    public static void main(String[] args) {
        new Kaji("data/admin").run();
    }
}
