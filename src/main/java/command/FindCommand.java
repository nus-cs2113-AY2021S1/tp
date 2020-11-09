package command;


import event.Event;
import eventlist.EventList;
import exception.NoMatchingEventException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;
import usercommunication.UserInfo;

import java.util.ArrayList;

/**
 * Represents the command call when the user want to find the tasks having a certain keyword.
 */
public class FindCommand extends Command {
    private final String filterString;

    public FindCommand(String filterString) {
        this.filterString = filterString;
    }

    /**
     * Find the tasks having the keyword provided by the user, and print that filtered list.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @param userInfo  personal information and settings about the user.
     * @throws NoMatchingEventException the user trying to find a Task with a certain keyword, but such task does not
     *                                  exist in the list.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage,
                        UserInfo userInfo)
            throws NoMatchingEventException {
        ArrayList<Event> filteredEventList = events.filterWith(filterString);
        if (filteredEventList.size() == 0) {
            throw new NoMatchingEventException();
        }
        ui.printFilteredEventList(filteredEventList);
    }
}
