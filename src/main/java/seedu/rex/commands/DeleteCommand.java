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

    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage)
            throws RexException {

        String nric = extractNric(trimmedCommand, COMMAND_WORD);
        if (patients.isExistingPatient(nric)) {
            Patient deletedPatient = patients.deletePatient(nric);
            ui.showPatientDeleted(deletedPatient);
            int i = 0;
            while (i < appointments.size()) {
                String tempNric = appointments.get(i).getPatient().getNric();
                if (tempNric.contentEquals(nric)) {
                    appointments.remove(i);
                } else {
                    i++;
                }
            }
        } else {
            ui.printPatientNotFound(nric);
        }
    }
}
