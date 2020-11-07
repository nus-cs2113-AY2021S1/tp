import command.Command;
import eventlist.EventList;
import exception.CreatingFileException;
import exception.DataFileNotFoundException;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import parser.Parser;
import storage.Storage;
import ui.UI;
import usercommunication.UserInfo;

/**
 * Main entry-point for the NUSchedule application.
 */
public class NuSchedule {

    private Storage storage;
    private EventList events;
    private static BusStopList busStops;
    private static LocationList locations;
    private final UI ui;
    private UserInfo userInfo = new UserInfo("", "");

    public NuSchedule(String... filePaths) {
        ui = new UI();

        busStops = new BusStopList();
        locations = new LocationList();

        try {
            storage = new Storage(filePaths);
        } catch (CreatingFileException e) {
            ui.showError(e.getMessage());
        }
        try {
            events = new EventList(storage.loadEvents(locations));
            userInfo = storage.loadUserInfo();
        } catch (NuScheduleException e) {
            ui.showLoadingError();
            events = new EventList();
        }

        storage.loadBusStopData(busStops.getBusStopList());
        storage.loadLocationData(locations.getLocationList());
        // ui.printBusStopList(busStops.getBusStopList());
        // ui.printLocationList(locations.getLocationList());

    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.printGreetingMessage(userInfo);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                int size = events.getSize();
                Command c = Parser.parse(fullCommand, locations, size);
                c.execute(events, locations, busStops, ui, storage);
                isExit = c.isExit();
            } catch (NuScheduleException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }


    public static void main(String[] args) {
        new NuSchedule("data/events.txt", "data/UserInfo.txt").run();
    }
}
