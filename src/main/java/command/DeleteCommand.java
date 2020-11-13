package command;


import eventlist.EventList;
import exception.UndefinedEventException;
import exception.WritingFileException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;
import usercommunication.UserInfo;

/**
 * Represents the command call when the user deletes some event.
 */
public class DeleteCommand extends Command {
    private final int eventIndex;

    public DeleteCommand(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    /**
     * Delete the event, and update the file.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @param userInfo  personal information and settings about the user.
     * @throws UndefinedEventException the user trying to operate (delete/edit/done) some events that does not exist.
     * @throws WritingFileException    the file is not correctly written.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage,
                        UserInfo userInfo)
            throws UndefinedEventException, WritingFileException {
        if (eventIndex <= -1 || eventIndex >= events.getSize()) {
            throw new UndefinedEventException(eventIndex + 1);
        }
        ui.printDeleteMessage(events.get(eventIndex));
        events.remove(eventIndex);
        ui.printNumEvent(events.getSize());
        storage.writeFile(events.getEventList());
    }
}
