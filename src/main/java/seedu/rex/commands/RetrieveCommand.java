package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

public class RetrieveCommand extends Command {

    public static final String COMMAND_WORD = "retrieve";
    private final String trimmedCommand;

    public RetrieveCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Retrieves patient from patient list using details inputted by the user.
     *
     * @param patients PatientList object.
     * @param ui       Ui object.
     * @param storage  Storage object.
     * @throws RexException If there is issue executing command.
     */
    @Override
    public void execute(PatientList patients, Ui ui, Storage storage) throws RexException {
        String nric = extractNric(trimmedCommand, COMMAND_WORD);
        int index = patients.getExistingPatient(nric);
        if (index < 0) {
            throw new RexException("No such patient!");
        }
        ui.showPatient(patients.getPatientUsingIndex(index));
    }
}
