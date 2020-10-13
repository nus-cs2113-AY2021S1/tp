package seedu.rex.commands;

import seedu.rex.data.AppointmentList;
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
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage) throws RexException {
        String nric = extractNric(trimmedCommand, COMMAND_WORD);
        if (!patients.isExistingPatient(nric)) {
            ui.printPatientNotFound(nric);
            patients.addNewPatient(ui.getPatientName(), nric, ui.getPatientDateOfBirth());
            ui.showLine();
        }


    }
}
