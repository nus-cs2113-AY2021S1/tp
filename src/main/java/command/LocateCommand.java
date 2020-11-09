package command;

import event.Event;
import eventlist.EventList;
import exception.InvalidEventIndexException;
import exception.InvalidLocationException;
import exception.NuScheduleException;
import exception.UnknownErrorException;
import location.Location;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

/**
 * Represents the command call when the user wants to locate an event.
 */
public class LocateCommand extends Command {
    private String input;

    public LocateCommand(String input) {
        this.input = input;
    }

    /**
     * Provides location information when user gives a number within size of EventList or name of a location.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage) {
        int eventNum;
        if (locations.checkIfInteger(input)) {
            eventNum = Integer.parseInt(input) - 1;
            Location location = events.get(eventNum).getLocation();
            System.out.println(events.get(eventNum).getDescription() + " is located at:");
            System.out.println(location);
        } else if (locations.checkValidLocation(input)) {
            Location location = locations.findLocation(input);
            System.out.println("Location Information: ");
            System.out.println(location);
        }
    }
}
