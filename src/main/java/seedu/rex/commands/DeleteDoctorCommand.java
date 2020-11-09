package seedu.rex.commands;

import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Doctor;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

/**
 * Deletes doctor.
 */
public class DeleteDoctorCommand extends Command {
    public static final String COMMAND_WORD = "nodoctor";
    private final String trimmedCommand;

    public DeleteDoctorCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Executes command.
     *
     * @param patients     PatientList object.
     * @param doctors      DoctorList object.
     * @param appointments AppointmentList object.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If there is issue executing command.
     */
    @Override
    public void execute(PatientList patients, DoctorList doctors, AppointmentList appointments, Ui ui, Storage storage)
            throws RexException {

        String doctorName = trimmedCommand.replaceFirst("(?i)" + COMMAND_WORD, "").trim().toUpperCase();

        if (doctors.isExistingDoctor(doctorName)) {
            Doctor deletedDoctor = doctors.deleteDoctor(doctorName);
            assert deletedDoctor != null : "Deleted doctor is null!";

            ui.showDoctorDeleted(deletedDoctor);

            for (int i = 0; i < appointments.getSize(); i++) {
                if (appointments.getAppointmentByIndex(i).getDoctor() == null) {
                    continue;
                }
                String tempName = appointments.getAppointmentByIndex(i).getDoctor().getName();
                if (tempName.equals(doctorName)) {
                    appointments.getAppointmentByIndex(i).setPatient(null);
                    appointments.getAppointmentByIndex(i).removeBooking();
                    appointments.getAppointmentByIndex(i).setDoctor(null);
                    break;
                }
            }
        } else {
            ui.printDoctorNotFound(doctorName);
        }
        storage.saveAppointments(appointments);
        storage.saveDoctors(doctors);
    }
}
