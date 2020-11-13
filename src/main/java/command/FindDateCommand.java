package command;


import event.Event;
import eventlist.EventList;
import exception.NoEventDateException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;
import usercommunication.UserInfo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the command call when the user want to find the tasks in certain date.
 */
public class FindDateCommand extends Command {
    private final LocalDate date;

    public FindDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Execute the command based on the specific command type.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @param userInfo  personal information and settings about the user.
     * @throws NoEventDateException the user trying to find a Event with a certain date, but such Event does not
     *                              exist in the list.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage,
                        UserInfo userInfo)
            throws NoEventDateException {
        ArrayList<Event> filteredEventList = events.filterDateWith(date);
        if (filteredEventList.size() == 0) {
            throw new NoEventDateException();
        }
        ui.printFilteredDateEventList(filteredEventList);
    }
}
