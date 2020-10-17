package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.data.hospital.Patient;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.ArrayList;

/**
 * Deletes a <code>Patient</code>'s data based on his NRIC.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final String trimmedCommand;

    public DeleteCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Deletes patients using NRIC.
     *
     * @param patients     PatientList object.
     * @param appointments ArrayList of appointment.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If NRIC has issues.
     */
    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage)
            throws RexException {

        String nric = extractNric(trimmedCommand, COMMAND_WORD);
        if (patients.isExistingPatient(nric)) {
            Patient deletedPatient = patients.deletePatient(nric);
            ui.showPatientDeleted(deletedPatient);

            for (int i = 0; i < appointments.size(); i++) {
                String tempNric = appointments.get(i).getPatient().getNric();
                if (tempNric.contentEquals(nric)) {
                    appointments.remove(i);
                    break;
                }
            }
        } else {
            ui.printPatientNotFound(nric);
        }
        storage.save(patients);
    }
}
