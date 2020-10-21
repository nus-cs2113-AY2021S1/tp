package command;

import eventlist.EventList;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

public class SortCommand extends Command {
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage) throws NuScheduleException {
        events.sortEvent();
        ui.printSortEventMessage();
    }
}
