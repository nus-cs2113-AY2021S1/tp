package seedu.rex.commands;

import seedu.rex.data.AppointmentList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

public class ListPatientsCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * List patients.
     *
     * @param patients     PatientList object.
     * @param appointments ArrayList of appointment.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If there is issue executing command.
     */
    @Override
    public void execute(PatientList patients, AppointmentList appointments, Ui ui, Storage storage)
            throws RexException {

        ui.listPatients(patients);
    }
}
