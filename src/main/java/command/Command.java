package command;

import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import eventlist.EventList;
import ui.UI;
import exception.NuScheduleException;

/**
 * Represents the command to be executed.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Execute the command based on the specific command type.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @throws NuScheduleException the exceptions can happen in this program,
     *                             to be handled based on the specific exception.
     */
    public abstract void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage)
            throws NuScheduleException;

    public boolean isExit() {
        return isExit;
    }
}
