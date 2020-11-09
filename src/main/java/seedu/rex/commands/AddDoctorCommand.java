package seedu.rex.commands;

import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Doctor;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

/**
 * Adds doctor to DoctorList.
 */
public class AddDoctorCommand extends Command {
    public static final String COMMAND_WORD = "doctor";
    private final String trimmedCommand;

    public AddDoctorCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Adds doctor with name.
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
            throw new RexException("A doctor with this name is already registered!");
        }

        Doctor newDoctor = new Doctor(doctorName);
        doctors.addDoctor(newDoctor);

        ui.showDoctorAdded(newDoctor);

        assert !doctors.getDoctors().isEmpty() : "No doctors!";
        storage.saveDoctors(doctors);
    }
}
