package seedu.rex.commands;

import seedu.rex.Rex;
import seedu.rex.data.PatientList;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Creates appointment.
 */
public class CreateAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "create";


    /**
     * Creates appointment using date given.
     *
     * @param patients     PatientList object.
     * @param appointments ArrayList of appointment.
     * @param ui           Ui object.
     * @param storage      Storage object.
     */
    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage) {
        assert patients != null : "patient ArrayList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";
        Rex.logger.log(Level.INFO, "going to create appointment");
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
