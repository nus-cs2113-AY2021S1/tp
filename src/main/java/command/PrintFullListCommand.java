package command;

import exception.NuScheduleException;
import storage.Storage;
import eventlist.EventList;
import ui.UI;

public class PrintFullListCommand extends Command {

    /**
     * Prints the list of Events.
     *
     * @param events  the list of Events.
     * @param ui      do outputs.
     * @param storage store the data.
     * @throws NuScheduleException the exceptions can happen in this function,
     *                             to be handled based on the specific exception.
     */
    @Override
    public void execute(EventList events, UI ui, Storage storage) throws NuScheduleException {
        ui.printEventList(events.getEventList());
    }
}
