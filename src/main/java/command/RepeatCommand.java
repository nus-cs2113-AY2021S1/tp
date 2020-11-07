package command;

import eventlist.EventList;
import exception.NuScheduleException;
import exception.UndefinedEventException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

/**
 * Represents the command to repeat certain classes for several weeks.
 */
public class RepeatCommand extends Command {
    private int index;
    private int numWeeks;//repeat the event for numWeeks weeks
    private boolean isAllClasses = false;

    public RepeatCommand(int index, int numWeeks) {
        this.index = index;
        this.numWeeks = numWeeks;
    }

    public RepeatCommand(boolean isAllClasses, int numWeeks) {
        this.isAllClasses = true;
        this.numWeeks = numWeeks;
    }

    /**
     * Execute the command based on the input. If the input is "repeat INDEX NUMBER_OF_WEEKS", repeat the event for
     * NUMBER_OF_WEEKS.
     * If the input is "repeat all NUMBER_OF_WEEKS", repeat all the classes happening in the current week for
     * NUMBER_OF_WEEKS.
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
        if (isAllClasses) {
            events.repeatAllClasses(numWeeks);
            ui.printRepeatAll(numWeeks);
        } else if (index < 0 || index >= events.getSize()) {
            throw new UndefinedEventException(index + 1);
        } else {
            events.repeatEvent(index, numWeeks);
            ui.printRepeatEvent(numWeeks);
        }

        storage.writeFile(events.getEventList());
    }
}
