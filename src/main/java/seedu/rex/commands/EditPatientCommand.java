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
 * Edits a patient from the list of patients.
 */
public class EditPatientCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    private final String trimmedCommand;

    public EditPatientCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Edits an existing patient to the patient list using details inputted by the user.
     *
     * @param patients     PatientList object.
     * @param doctors      DoctorList object.
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

        if (!patients.isExistingPatient(nric)) {
            throw new RexException("A patient with this NRIC has not been registered!");
        }

        int index = patients.editExistingPatient(ui.getPatientName(), nric, ui.getPatientDateOfBirth());
        assert index > -1 : "Invalid index!";
        ui.showLine();
        ui.showPatientEdited(patients.getPatientUsingIndex(index));
        storage.savePatients(patients);
    }
}