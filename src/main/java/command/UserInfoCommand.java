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
    private String name;
    private String userType;

    public UserInfoCommand(String name, String userType) {
        this.name = name;
        this.userType = userType;
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
     * @throws NuScheduleException the exceptions can happen in this program,
     *                             to be handled based on the specific exception.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage,
                        UserInfo userInfo)
            throws NuScheduleException {
        userInfo.setName(name);
        userInfo.setType(userType);
        storage.writeUserInfo(userInfo);
        ui.helloWithName(userInfo.getName());
    }
}
