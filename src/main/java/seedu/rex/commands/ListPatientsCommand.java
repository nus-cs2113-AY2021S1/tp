package seedu.rex.commands;

import seedu.rex.data.AppointmentList;
import seedu.rex.data.DoctorList;
import seedu.rex.data.PatientList;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

public class ListPatientsCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * List patients.
     *  @param patients     PatientList object.
     * @param doctors DoctorList object.
     * @param appointments AppointmentList object.
     * @param ui           Ui object.
     * @param storage      Storage object.
     */
    @Override
    public void execute(PatientList patients, DoctorList doctors, AppointmentList appointments, Ui ui, Storage storage) {

        ui.listPatients(patients);
    }
}
