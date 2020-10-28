package command;

import event.Event;
import eventlist.EventList;
import exception.NoEventDateRemindException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the command call when the user wants a list of events within the day.
 */
public class ReminderCommand extends Command {
    private final LocalDate filterDate;

    public ReminderCommand (){
        filterDate = LocalDate.now();
    }

    /**
     * Command to print list of events within the day when called.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage)
            throws NoEventDateRemindException {
        ArrayList<Event> filteredEventList = events.filterDateWith(filterDate);
        if (filteredEventList.size() == 0) {
            throw new NoEventDateRemindException();
        }
        ui.printFilteredEventList(filteredEventList);
    }
}
