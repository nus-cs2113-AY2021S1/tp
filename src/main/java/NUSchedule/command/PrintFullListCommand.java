package NUSchedule.command;

import NUSchedule.Storage.Storage;
import NUSchedule.TaskList.TaskList;
import NUSchedule.UI.UI;
import NUSchedule.Exception.DukeException;

public class PrintFullListCommand extends Command {

    /**
     * Prints the list of tasks.
     *
     * @param tasks   the list of tasks
     * @param ui      do outputs
     * @param storage store the data
     * @throws DukeException the exceptions can happen in this function, to be handled based on the specific exception
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printTaskList(tasks.getTaskList());
    }
}
