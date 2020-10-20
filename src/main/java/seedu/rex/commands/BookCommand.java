package seedu.rex.commands;

import seedu.rex.Rex;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.logging.Level;

/**
 * Books appointments.
 */
public class BookCommand extends Command {

    public static final String COMMAND_WORD = "book";
    private final String trimmedCommand;

    public BookCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Books appointment for patients.
     *
     * @param patients     PatientList object.
     * @param appointments AppointmentList object.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If appointment cannot be booked.
     */
    @Override
    public void execute(PatientList patients, AppointmentList appointments, Ui ui, Storage storage)
            throws RexException {

        assert patients != null : "patient ArrayList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";
        Rex.logger.log(Level.INFO, "going to extract NRIC");
        String nric = extractNric(trimmedCommand, COMMAND_WORD);

        if (appointments.isEmpty()) {
            throw new RexException("No appointment sessions!");
        }

        if (!patients.isExistingPatient(nric)) {
            ui.printPatientNotFound(nric);
            ui.showCreatePatientMessage(nric);
            Rex.logger.log(Level.INFO, "going to add patient...");
            new AddCommand("add " + nric).execute(patients, appointments, ui, storage);
            ui.showLine();
        }

        try {
            String indexSelected = ui.getAppointmentToBook(appointments);
            int index = Integer.parseInt(indexSelected) - 1;
            if (index < 0 || index >= appointments.getSize()) {
                throw new RexException("Index error!");
            }
            Rex.logger.log(Level.INFO, "booking appointment for patient...");
            appointments.getAppointmentByIndex(index).book(patients.getPatientFromNric(nric));
            ui.showAppointmentBookedMessage(appointments.getAppointmentByIndex(index));

            assert !appointments.isEmpty() : "No appointments!";
            storage.saveAppointments(appointments);
        } catch (NumberFormatException e) {
            throw new RexException("Index error!");
        }


    }
}
