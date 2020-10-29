package command;

import event.Event;
import eventlist.EventList;
import exception.NuScheduleException;
import exception.WritingFileException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;


/**
 * Represents the command call when the user adds a new event.
 */
public class AddCommand extends Command {
    private final Event event;

    /**
     * Sets the task to be added to the list.
     *
     * @param event The event to be added.
     */
    public AddCommand(Event event) {
        this.event = event;
    }

    /**
     * Adds the appropriate type of event/task to the list.
     *
     * @param events    the list of events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @throws WritingFileException the file is not correctly written.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage)
            throws WritingFileException {
        ui.printConflictEvents(events.checkConflictTiming(event));
        events.addEvent(event);
        ui.printAddEventMessage(event);
        ui.printNumEvent(events.getSize());
        storage.writeFile(events.getEventList());
    }
}
