package seedu.rex.commands;

import seedu.rex.data.AppointmentList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class CreateAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "create";
    private final String trimmedCommand;

    public CreateAppointmentCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }


    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage) throws RexException {
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
