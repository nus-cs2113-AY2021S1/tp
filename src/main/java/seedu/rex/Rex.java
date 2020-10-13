package seedu.rex;

import seedu.rex.commands.Command;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.parser.Parser;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class.
 */
public class Rex {

    private final Storage storage;
    private final Ui ui;
    private PatientList patients;
    private static Logger logger;

    /**
     * Initializes Rex.
     *
     * @param filePath Path to store and load patients.
     */
    public Rex(String filePath) {
        assert filePath != null && !filePath.equals("") : "File path cannot be empty!";

        ui = new Ui();
        storage = new Storage(filePath);
        logger = Logger.getLogger("Rex");
        try {
            logger.log(Level.INFO, "going to load patients");
            patients = new PatientList(storage.load());
            logger.log(Level.INFO, "loaded patients");
        } catch (RexException e) {
            logger.log(Level.INFO, "patients loading error");
            ui.showLoadingError();
            patients = new PatientList();
        }
    }

    /**
     * Starts Rex.
     *
     * @param args Command line argument.
     */
    public static void main(String[] args) {
        new Rex("data/patients.txt").run();
    }

    /**
     * Runs Rex.
     */
    public void run() {
        assert ui != null : "ui is null";
        assert patients != null : "patient ArrayList is null";
        assert storage != null : "storage is null";

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(patients, ui, storage);
                isExit = c.isExit();
            } catch (RexException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
