package NUSchedule.command;

import NUSchedule.eventList.EventList;
import NUSchedule.storage.Storage;
import NUSchedule.ui.UI;

/**
 * Represents the command call when the user exits this program.
 */
public class ExitCommand extends Command {
    /**
     * Mark the status of the program, isExit, as true.
     * Also prints the exit message.
     *
     * @param tasks   the list of tasks.
     * @param ui      do outputs.
     * @param storage store the data.
     */
    @Override
    public void execute(EventList tasks, UI ui, Storage storage) {
        isExit = true;
        ui.printExitMessage();

    }
}
