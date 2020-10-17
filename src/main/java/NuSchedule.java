import command.Command;
import eventlist.EventList;
import exception.CreatingFileException;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import parser.Parser;
import storage.Storage;
import ui.UI;

import exception.LoadingException;

/**
 * Main entry-point for the NUSchedule application.
 */
public class NuSchedule {

    private Storage storage;
    private EventList events;
    private static BusStopList busStops;
    private static LocationList locations;
    private UI ui;


    public NuSchedule(String filePath) {
        ui = new UI();

        busStops = new BusStopList();
        locations = new LocationList();
        //busStops.loadBusStopData();
        //locations.loadLocationData();

        try {
            storage = new Storage(filePath);
        } catch (CreatingFileException e) {
            ui.showError(e.getMessage());
        }
        try {
            events = new EventList(storage.loadEvents());
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
        ui.printGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
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
        new NuSchedule("data/events.txt").run();
        //todo add more files for different purposes
    }
}
