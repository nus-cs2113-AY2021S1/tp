package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.ArrayList;

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
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage)
            throws RexException {

        ui.listPatients(patients);
    }
}
