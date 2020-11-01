package command;

import event.Event;
import eventlist.EventList;
import exception.EditIndexOutOfBoundsException;
import exception.NuScheduleException;
import exception.UndefinedEventException;
import exception.WritingFileException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

/**
 * Represents the command call when the user adds a new event.
 */
public class EditCommand extends Command {
    private final int index;
    private final Event event;

    public EditCommand(Event event, int index) {
        this.event = event;
        this.index = index;
    }

    /**
     * Edits the corresponding event/task in the list.
     *
     * @param events    the list of events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @throws UndefinedEventException the user trying to operate (delete/edit/done) some events that does not
     *                                 exist.
     * @throws WritingFileException    Represents the exception when the file is not correctly written.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage)
            throws UndefinedEventException, WritingFileException {
        if (index >= events.getSize() || index == -1) {
            throw new UndefinedEventException(index + 1);
        }
        events.editEvent(event, index);
        ui.printConflictEvents(events.checkConflictTiming(event));
        ui.printEditEventMessage(event);
        storage.writeFile(events.getEventList());
    }
}
