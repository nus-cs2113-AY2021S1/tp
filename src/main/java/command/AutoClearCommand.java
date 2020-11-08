package command;

import eventlist.EventList;
import exception.EmptyEventListException;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

import java.time.LocalDate;

/**
 * Represents the command call when the user wants to turn on/off the auto-clear function
 * if this function is on, events which ends one month earlier will be cleared
 */
public class AutoClearCommand extends Command {

    protected boolean isOn;
    protected LocalDate clearDate;

    public AutoClearCommand() {
        clearDate = LocalDate.now().minusMonths(1);
        isOn = false;
    }

    /**
     * Execute the command based on the specific command type.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @throws EmptyEventListException the exceptions when the user try to clear an empty list.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage) throws EmptyEventListException{
        if (!isOn) {
            ui.printAutoClearOn();
            events.clearBefore(clearDate);
        } else {
            ui.printAutoClearOff();
        }
    }
}
