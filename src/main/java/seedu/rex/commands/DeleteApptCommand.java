package seedu.rex.commands;

import seedu.rex.Rex;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Deletes appointment.
 */
public class DeleteApptCommand extends Command {

    public static final String COMMAND_WORD = "deleteappt";
    private final String trimmedCommand;


    public DeleteApptCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Deletes appointment using NRIC given.
     *
     * @param patients     PatientList object.
     * @param doctors      DoctorList object.
     * @param appointments AppointmentList object.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If the input NRIC does not exist in system
     */
    @Override
    public void execute(PatientList patients, DoctorList doctors, AppointmentList appointments, Ui ui,
                        Storage storage) throws RexException {
        assert patients != null : "patient ArrayList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";
        Rex.logger.log(Level.INFO, "going to extract NRIC");
        String nric = extractNric(trimmedCommand, COMMAND_WORD);

        if (!patients.isExistingPatient(nric)) {
            throw new RexException("A patient with this NRIC has not been registered!");
        }

        try {
            ArrayList<Appointment> patientAppointments = new ArrayList<>();
            for (int i = 0; i < appointments.getSize(); i++) {
                if (appointments.getAppointmentByIndex(i).getPatient() != null
                        && appointments.getAppointmentByIndex(i).getPatient().getNric().equals(nric)) {
                    patientAppointments.add(appointments.getAppointmentByIndex(i));
                }
            }
            ui.showDeleteAppointmentMessage();
            Appointment appointmentToEdit = ui.getItemOfArrayList(patientAppointments);
            ui.showDeleteAppointmentMessage(appointmentToEdit);
            appointmentToEdit.removeBooking();

            assert !appointments.isEmpty() : "No appointments!";
            storage.saveAppointments(appointments);
        } catch (NumberFormatException e) {
            throw new RexException("Index error!");
        }

    }
}
