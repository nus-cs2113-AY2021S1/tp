package command;

import event.Event;
import eventlist.EventList;
import exception.EmptyEventListException;
import exception.NoDateBeforeException;
import exception.NoEventOneMonthAgoException;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the command call when the user wants to turn on/off the auto-clear function
 * everytime the user input the function, events of one month ago will be cleared
 */
public class AutoClearCommand extends Command {

    protected LocalDate clearDate;

    public AutoClearCommand() {
        clearDate = LocalDate.now().minusMonths(1);
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
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage) throws EmptyEventListException, NoEventOneMonthAgoException {
        if (events != null) {
            ArrayList<Event> filteredEventList = events.filterDateBefore(clearDate);
            if (filteredEventList.size() == 0) {
                throw new NoEventOneMonthAgoException();
            } else {
                ui.printAutoClearOn();
                events.clearBefore(clearDate);
            }
        } else {
            throw new EmptyEventListException();
        }
    }
}
