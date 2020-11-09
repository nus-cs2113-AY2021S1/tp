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
 * List appointments of patient.
 */
public class ListApptCommand extends Command {

    public static final String COMMAND_WORD = "appointments";
    private final String trimmedCommand;

    public ListApptCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Lists appointments of a patient.
     *
     * @param patients     PatientList object.
     * @param doctors      DoctorList object.
     * @param appointments AppointmentList object.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If the input NRIC does not exist in system
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
        Patient targetPatient = patients.getPatientFromNric(nric);
        assert targetPatient != null : "Null target patient!";
        ui.showAppointmentsListHeader(nric);

        int i;
        for (i = 0; i < appointments.getSize(); i++) {
            Patient tempPatient = appointments.getAppointmentByIndex(i).getPatient();
            if (tempPatient != null && tempPatient.equals(targetPatient)) {
                ui.showAppointmentLine(appointments.getAppointmentByIndex(i), i + 1);
            }
        }
        if (i == 0) {
            ui.showNoBookedAppointmentsMessage();
        }
    }
}
