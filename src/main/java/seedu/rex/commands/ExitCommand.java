package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.ArrayList;

/**
 * Exits program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Says goodbye to user and exits Rex.
     *
     * @param patients PatientList object.
     * @param ui       Ui object.
     * @param storage  Storage object.
     */
    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage) {
        isExit = true;
        ui.showExit();
    }
}
