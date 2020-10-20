package command;

import event.Event;
import eventlist.EventList;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

/**
 * Represents the command call when the user adds a new event.
 */
public class EditCommand extends Command {
    private final int index;
    private final Event event;

    public EditCommand(Event event, int index) {
        this.event = event;
        this.index = index;
    }

    /**
     * Edits the corresponding event/task in the list.
     *
     * @param events    the list of events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage)
            throws NuScheduleException {
        events.editEvent(event, index);
        ui.printEditEventMessage(event);
        ui.printNumEvent(events.getSize());
    }
}
