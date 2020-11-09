package command;

import event.Event;
import eventlist.EventList;
import exception.EmptyEventListException;
import exception.WritingFileException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;
import usercommunication.UserInfo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the command call when the user wants to turn on/off the auto-clear function.
 * Everytime the user input the function, events of one month ago will be cleared.
 */
public class AutoClearCommand extends Command {

    protected boolean isAutoClear;

    public AutoClearCommand(boolean isAutoClear) {
        this.isAutoClear = isAutoClear;
    }

    /**
     * Set the AutoClear flag as ON or OFF.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @param userInfo  personal information and settings about the user.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage,
                        UserInfo userInfo) throws WritingFileException {
        userInfo.setAutoClear(isAutoClear);
        storage.writeUserInfo(userInfo);
        if (isAutoClear) {
            ui.printAutoClearOn();
        } else {
            ui.printAutoClearOff();
        }
    }

    /**
     * Clear all events one month ago.
     *
     * @param events   the list of Events.
     * @param storage  store the data.
     * @param userInfo User's information.
     * @throws WritingFileException the file is not correctly written.
     */
    public static void autoClear(EventList events, Storage storage, UserInfo userInfo) throws WritingFileException {
            if (userInfo.isAutoClear() && events != null) {
                if (events.getSize() != 0) {
                    try {
                        events.clearBefore(LocalDate.now().minusMonths(1));
                    } catch (EmptyEventListException e) {
                        return;//this exception is unreachable because of the if condition
                    }
                    storage.writeFile(events.getEventList());
                }
            }

    }
}
