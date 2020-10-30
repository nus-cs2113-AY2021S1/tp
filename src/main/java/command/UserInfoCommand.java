package command;

import eventlist.EventList;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;
import usercommunication.UserInfo;

/**
 * Represents the command call when the user wants to change the type of himself or herself.
 */
public class UserInfoCommand extends Command {
    private UserInfo userInfo;

    public UserInfoCommand(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

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
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage)
            throws NuScheduleException {
        storage.writeUserInfo(userInfo);
        ui.helloWithName(userInfo.getName());
    }
}
