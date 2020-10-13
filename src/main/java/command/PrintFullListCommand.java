package command;

import storage.Storage;
import eventList.EventList;
import ui.UI;
import exception.NUScheduleException;

public class PrintFullListCommand extends Command {

    /**
     * Prints the list of Events.
     *
     * @param Events   the list of Events.
     * @param ui      do outputs.
     * @param storage store the data.
     * @throws NUScheduleException the exceptions can happen in this function,
     * to be handled based on the specific exception.
     */
    @Override
    public void execute(EventList Events, UI ui, Storage storage) throws NUScheduleException {
        ui.printEventList(Events.getEventList());
    }
}
