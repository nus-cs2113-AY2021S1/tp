package seedu.rex.commands;

import seedu.rex.Rex;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.logging.Level;

/**
 * Books appointments.
 */
public class BookApptCommand extends Command {

    public static final String COMMAND_WORD = "book";
    private final String trimmedCommand;

    public BookApptCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Books appointment for patients.
     *
     * @param patients     PatientList object.
     * @param doctors      DoctorList object.
     * @param appointments AppointmentList object.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If appointment cannot be booked.
     */
    @Override
    public void execute(PatientList patients, DoctorList doctors, AppointmentList appointments, Ui ui, Storage storage)
            throws RexException {

        assert patients != null : "patient ArrayList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";

        if (appointments.isEmpty()) {
            throw new RexException("No appointment sessions!");
        }

        Rex.logger.log(Level.INFO, "going to extract NRIC");
        String nric = extractNric(trimmedCommand, COMMAND_WORD);

        Rex.logger.log(Level.INFO, "going to get doctor's name");
        String doctorName = ui.getDoctorName();
        if (!doctors.isExistingDoctor(doctorName)) {
            throw new RexException("No such doctor!");
        }

        if (!patients.isExistingPatient(nric)) {
            throw new RexException("Patient " + nric + " not found in database!");
        }

        try {
            ui.showAvailableAppointmentsMessage();
            Rex.logger.log(Level.INFO, "booking appointment for patient and doctor...");
            Appointment chosenAppointment = ui.getItemOfArrayList(appointments.getAvailableAppointments());
            chosenAppointment.bookPatient(patients.getPatientFromNric(nric));
            chosenAppointment.bookDoctor(doctors.getDoctorFromName(doctorName));
            ui.showAppointmentBookedMessage(chosenAppointment);

            assert !appointments.isEmpty() : "No appointments!";
            storage.saveAppointments(appointments);
        } catch (NumberFormatException e) {
            throw new RexException("Index error!");
        }


    }
}
