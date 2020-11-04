package command;

import event.Event;
import eventlist.EventList;
import exception.EditIndexOutOfBoundsException;
import exception.EditNoEndTimeException;
import exception.EndBeforeStartEventException;
import exception.NuScheduleException;
import exception.UndefinedEventException;
import exception.WritingFileException;
import location.Location;
import location.OnlineLocation;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

import java.time.LocalDateTime;

/**
 * Represents the command call when the user adds a new event.
 */
public class EditCommand extends Command {
    private final int index;
    private final String[] editInformation;
    private final LocalDateTime[] startEnd;
    
    private final Location location;
    private final OnlineLocation onlineLocation;

    public EditCommand(int index, String[] editInformation, LocalDateTime[] startEnd, Location location,
                       OnlineLocation onlineLocation) {
        this.index = index;
        this.editInformation = editInformation;
        this.startEnd = startEnd;
        this.location = location;
        this.onlineLocation = onlineLocation;
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
            throws UndefinedEventException, WritingFileException, EndBeforeStartEventException, EditNoEndTimeException {
        if (index >= events.getSize() || index == -1) {
            throw new UndefinedEventException(index + 1);
        }
        Event newEvent = events.editEvent(index, editInformation, startEnd, location, onlineLocation);
        ui.printConflictEvents(events.checkConflictTiming(newEvent));
        ui.printEditEventMessage(newEvent);
        storage.writeFile(events.getEventList());
    }

    public static String[] newEditInformation() {
        UI ui = new UI();
        String[] editInformation = ui.readEditCommand();
        return editInformation;
    }
}
