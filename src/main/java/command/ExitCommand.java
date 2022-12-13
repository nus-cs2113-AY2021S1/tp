package command;

import eventlist.EventList;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;
import usercommunication.UserInfo;

/**
 * Represents the command call when the user exits this program.
 */
public class ExitCommand extends Command {
    /**
     * Mark the status of the program, isExit, as true.
     * Also prints the exit message.
     *
     * @param events    the list of tasks.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @param userInfo  personal information and settings about the user.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage,
                        UserInfo userInfo) {
        isExit = true;
        ui.printExitMessage();
    }
}
