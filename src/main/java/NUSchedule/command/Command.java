package NUSchedule.command;

import NUSchedule.storage.Storage;
import NUSchedule.eventList.EventList;
import NUSchedule.ui.UI;
import NUSchedule.exception.NUScheduleException;

/**
 * Represents the command to be executed.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Execute the command based on the specific command type.
     *
     * @param Events   the list of Events.
     * @param ui      do outputs.
     * @param storage store the data.
     * @throws NUScheduleException the exceptions can happen in this program, to be handled based on the specific exception.
     */
    public abstract void execute(EventList Events, UI ui, Storage storage) throws NUScheduleException;

    public boolean isExit() {
        return isExit;
    }
}
