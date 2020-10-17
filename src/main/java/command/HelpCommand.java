package command;

import eventlist.EventList;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;
/**
 * Represents the command call when the user asking for helps.
 */
public class HelpCommand extends Command {
    /**
     * Execute the command based on the specific command type.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage) {
    ui.printHelp();
    }
}
