package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Create appointment.
 */
public class CreateAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "create";


    /**
     * Create appointment using date given.
     *
     * @param patients     PatientList object.
     * @param appointments ArrayList of appointment.
     * @param ui           Ui object.
     * @param storage      Storage object.
     */
    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage) {
        String date = ui.getNewAppointmentDate();
        try {
            Appointment newAppointment = new Appointment(LocalDate.parse(date));
            appointments.add(newAppointment);
            ui.showAppointmentCreatedMessage();

        } catch (DateTimeParseException e) {
            ui.showDateInputError();
        }

    }
}
