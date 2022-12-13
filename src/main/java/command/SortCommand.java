package command;

import eventlist.EventList;
import exception.NuScheduleException;
import exception.WritingFileException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;
import usercommunication.UserInfo;

/**
 * Represents the command call when the user wants to sort the list.
 */
public class SortCommand extends Command {
    private final String type;

    public SortCommand(String type) {
        this.type = type;
    }

    /**
     * Sort the list with given condition.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @param userInfo  personal information and settings about the user.
     * @throws WritingFileException the file is not correctly written.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage,
                        UserInfo userInfo)
            throws WritingFileException {
        events.sortEvent(type);
        ui.printSortEventMessage();
        storage.writeFile(events.getEventList());
    }
}
