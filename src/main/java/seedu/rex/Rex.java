package seedu.rex;

import seedu.rex.commands.Command;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
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

    public static Logger logger;
    private static PatientList patients;
    private static DoctorList doctors;
    private final Storage storage;
    private final Ui ui;
    private AppointmentList appointments;

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
        logger.setLevel(Level.WARNING);

        try {
            logger.log(Level.INFO, "going to load patients");
            patients = new PatientList(storage.loadPatients());
            logger.log(Level.INFO, "loaded patients");
        } catch (RexException e) {
            logger.log(Level.INFO, "No patients found. Creating new patients list.");
            ui.showLoadingError();
            patients = new PatientList();
        }

        try {
            logger.log(Level.INFO, "going to load doctors");
            doctors = new DoctorList(storage.loadDoctors());
            logger.log(Level.INFO, "loaded doctors");
        } catch (RexException e) {
            logger.log(Level.INFO, "No doctors found. Creating new doctors list.");
            ui.showLoadingError();
            doctors = new DoctorList();
        }

        try {
            logger.log(Level.INFO, "going to load appointments");
            appointments = new AppointmentList(storage.loadAppointments());
            logger.log(Level.INFO, "loaded appointments");
        } catch (RexException e) {
            logger.log(Level.INFO, "No appointments found. Creating new appointments list.");
            ui.showLoadingError();
            appointments = new AppointmentList();
        }
    }

    /**
     * Starts Rex.
     *
     * @param args Command line argument.
     */
    public static void main(String[] args) {
        new Rex("data").run();
    }

    public static PatientList getPatients() {
        return patients;
    }

    public static DoctorList getDoctors() {
        return doctors;
    }

    /**
     * Runs Rex.
     */
    public void run() {
        assert ui != null : "ui is null";
        assert patients != null : "patient ArrayList is null";
        assert doctors != null : "patient ArrayList is null";
        assert storage != null : "storage is null";

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(patients, doctors, appointments, ui, storage);
                isExit = c.isExit();
            } catch (RexException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
