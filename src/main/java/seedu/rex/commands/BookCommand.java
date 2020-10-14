package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;
import java.util.ArrayList;

public class BookCommand extends Command {

    public static final String COMMAND_WORD = "book";
    private final String trimmedCommand;

    public BookCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage)
            throws RexException {
        String nric = extractNric(trimmedCommand, COMMAND_WORD);
        if (appointments.isEmpty()) {
            throw new RexException("No appointment sessions!");
        }
        if (!patients.isExistingPatient(nric)) {
            ui.printPatientNotFound(nric);
            ui.showCreatePatientMessage(nric);
            new AddCommand("add " + nric).execute(patients, appointments, ui, storage);
            ui.showLine();
        }
        String indexSelected = ui.getAppointmentToBook(appointments);
        try {
            int index = Integer.parseInt(indexSelected) - 1;
            if (index < 0 || index >= appointments.size()) {
                throw new RexException("Index error!");
            }
            appointments.get(index).book(patients.getPatientFromNric(nric));
            ui.showAppointmentBookedMessage(appointments.get(index));
        } catch (NumberFormatException e) {
            throw new RexException("Index error!");
        }


    }
}
