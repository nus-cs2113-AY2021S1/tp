package seedu.rex.commands;

import seedu.rex.Rex;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Patient;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.logging.Level;

/**
 * Deletes a <code>Patient</code>'s data based on his NRIC.
 */
public class DeletePatientCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final String trimmedCommand;

    public DeletePatientCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Deletes patients using NRIC.
     *
     * @param patients     PatientList object.
     * @param doctors      DoctorList object.
     * @param appointments AppointmentList object.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If NRIC has issues.
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
            Patient deletedPatient = patients.deletePatient(nric);
            assert deletedPatient != null : "Deleted patient is null!";

            ui.showPatientDeleted(deletedPatient);

            for (int i = 0; i < appointments.getSize(); i++) {
                Patient tempPatient = appointments.getAppointmentByIndex(i).getPatient();
                if (tempPatient != null && tempPatient.getNric().contentEquals(nric)) {
                    appointments.getAppointmentByIndex(i).setPatient(null);
                    appointments.getAppointmentByIndex(i).removeBooking();
                    appointments.getAppointmentByIndex(i).setDoctor(null);
                    break;
                }
            }
        } else {
            ui.printPatientNotFound(nric);
        }
        storage.savePatients(patients);
        storage.saveAppointments(appointments);
    }
}
