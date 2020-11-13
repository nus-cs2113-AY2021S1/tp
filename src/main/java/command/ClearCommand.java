package command;

import eventlist.EventList;
import exception.EmptyEventListException;
import exception.WritingFileException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;
import usercommunication.UserInfo;

/**
 * Represents the command call when the user clears all events.
 */
public class ClearCommand extends Command {
    /**
     * Execute the command based on the specific command type.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @param userInfo  personal information and settings about the user.
     * @throws EmptyEventListException the exceptions when the user try to clear an empty list.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage,
                        UserInfo userInfo)
            throws EmptyEventListException, WritingFileException {
        if (events.getSize() == 0) {
            throw new EmptyEventListException();
        }
        events.clearEvents();
        storage.writeFile(events.getEventList());
        ui.printClearEventsSuccessful();
    }
}
