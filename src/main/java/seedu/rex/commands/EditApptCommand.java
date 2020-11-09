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
 * Edits appointment.
 */
public class EditApptCommand extends Command {

    public static final String COMMAND_WORD = "editappt";
    private final String trimmedCommand;


    public EditApptCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Edits appointment using NRIC given.
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
            ui.showEditAppointmentMessage();
            Appointment appointmentToEdit = ui.getItemOfArrayList(patientAppointments);
            ui.showDeleteAppointmentMessage(appointmentToEdit);
            appointmentToEdit.removeBooking();

            String indexSelected = ui.getAppointmentToEdit(appointments);
            int index = Integer.parseInt(indexSelected) - 1;
            if (index < 0 || index >= appointments.getSize()) {
                throw new RexException("Index error!");
            }
            Rex.logger.log(Level.INFO, "going to get doctor's name");
            String doctorName = ui.getDoctorName();
            if (!doctors.isExistingDoctor(doctorName)) {
                throw new RexException("No such doctor!");
            }

            appointments.getAppointmentByIndex(index).bookPatient(patients.getPatientFromNric(nric));
            appointments.getAppointmentByIndex(index).bookDoctor(doctors.getDoctorFromName(doctorName));
            ui.showAppointmentBookedMessage(appointments.getAppointmentByIndex(index));

            assert !appointments.isEmpty() : "No appointments!";
            storage.saveAppointments(appointments);
        } catch (NumberFormatException e) {
            throw new RexException("Index error!");
        }

    }
}
