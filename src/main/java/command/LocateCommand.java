package command;

import event.Event;
import eventlist.EventList;
import exception.NuScheduleException;
import location.Location;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents the command call when the user wants to locate an event
 */
public class LocateCommand extends Command {
    private String input;

    public LocateCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage) throws NuScheduleException {
        int event_num;
        try {
            event_num = Integer.parseInt(input);
            Event event = events.get(event_num-1);
            Location location = event.getLocation();
            System.out.println(event.getDescription() + " is located at:");
            System.out.println(location);
        } catch (NumberFormatException e) {
            boolean checkValidLocation = locations.checkValidLocation(input);
            if (checkValidLocation) {
                Location location = locations.findLocation(input);
                System.out.println("Location Information: ");
                System.out.println(location);
            } else {
                System.out.println("Please input a valid location or event number.");
            }
        }
    }
}
