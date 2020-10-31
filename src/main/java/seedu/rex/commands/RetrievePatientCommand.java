package seedu.rex.commands;

import seedu.rex.Rex;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.logging.Level;

/**
 * Retrieves patient details.
 */
public class RetrievePatientCommand extends Command {

    public static final String COMMAND_WORD = "retrieve";
    private final String trimmedCommand;

    public RetrievePatientCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Retrieves patient from patient list using details inputted by the user.
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
        assert patients != null : "patient ArrayList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";
        Rex.logger.log(Level.INFO, "going to extract NRIC");
        String nric = extractNric(trimmedCommand, COMMAND_WORD);

        int index = patients.getExistingPatient(nric);
        assert index > -2 : "Unexpected index!";
        if (index < 0) {
            throw new RexException("No such patient!");
        }
        Rex.logger.log(Level.INFO, "going to show patients");
        ui.showPatient(patients.getPatientUsingIndex(index));
        Rex.logger.log(Level.INFO, "end of retrieve command");
    }
}
