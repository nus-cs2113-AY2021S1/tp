package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.data.hospital.Patient;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.ArrayList;

public class ListAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "appointments";
    private final String trimmedCommand;

    public ListAppointmentsCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Lists appointments of a patient
     *
     * @param patients     PatientList object.
     * @param appointments ArrayList of appointment.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If the input NRIC does not exist in system
     */
    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage)
            throws RexException {
        String nric = extractNric(trimmedCommand, COMMAND_WORD);
        if (!patients.isExistingPatient(nric)) {
            throw new RexException("A patient with this NRIC has not been registered!");
        }
        Patient targetPatient = patients.getPatientFromNric(nric);
        ui.showAppointmentsListHeader(nric);
        int counter = 0;
        for (Appointment appointment : appointments) {
            if (appointment.getPatient() == targetPatient) {
                counter++;
                ui.showAppointmentLine(appointment, counter);
            }
        }
        if (counter == 0) {
            ui.showNoBookedAppointmentsMessage();
        }
    }
}
