package command;

import eventlist.EventList;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

/**
 * Represent the command for print the locations.
 */
public class PrintLocationCommand extends Command {
    /**
     * Prints list of locations.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage) {
        ui.printLocationList(locations.getLocationList());
    }
}
