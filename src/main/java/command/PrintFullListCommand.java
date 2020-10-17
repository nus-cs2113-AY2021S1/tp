package command;

import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import eventlist.EventList;
import ui.UI;

public class PrintFullListCommand extends Command {

    /**
     * Prints the list of Events.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage) {
        ui.printEventList(events.getEventList());
    }
}
