package seedu.rex.commands;

import seedu.rex.Rex;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.logging.Level;

/**
 * Adds a patient to the list of patients.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private final String trimmedCommand;

    public AddCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Adds a new patient to the patient list using details inputted by the user.
     *
     * @param patients     PatientList object.
     * @param doctors DoctorList object.
     * @param appointments AppointmentList object.
     * @param ui           Ui object of the program.
     * @param storage      Storage object used for saving data to files.
     * @throws RexException If there is an error in the NRIC inputted by the user, the data fails
     *                      to save successfully, or the NRIC already exists in the patient list.
     */
    @Override
    public void execute(PatientList patients, DoctorList doctors, AppointmentList appointments, Ui ui, Storage storage)
            throws RexException {
        assert patients != null : "patient ArrayList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";
        Rex.logger.log(Level.INFO, "going to extract NRIC");
        String nric = extractNric(trimmedCommand, COMMAND_WORD);

        if (patients.isExistingPatient(nric)) {
            throw new RexException("A patient with this NRIC is already registered!");
        }

        Rex.logger.log(Level.INFO, "adding patient...");
        patients.addNewPatient(ui.getPatientName(), nric, ui.getPatientDateOfBirth());
        ui.showLine();

        ui.showPatientAdded(patients.getPatientUsingIndex(patients.getSize() - 1));

        assert !patients.getPatients().isEmpty() : "No patients!";
        storage.savePatients(patients);
    }
}
