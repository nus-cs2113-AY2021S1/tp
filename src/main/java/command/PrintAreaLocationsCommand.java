package command;

import eventlist.EventList;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

/**
 * Represents the command for printing locations available at some areas in NUS.
 */
public class PrintAreaLocationsCommand extends Command {
    String area;

    public PrintAreaLocationsCommand(String area) {
        this.area = area;
    }

    /**
     * Purely print the available locations at the area selected by the user.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage) {
        switch (area.toUpperCase()) {
        case "FOS":
            ui.printFosLocations();
            break;
        case "FOE":
            ui.printFoeLocations();
            break;
        case "FASS":
            ui.printFassLocations();
            break;
        case "SDE":
            ui.printSdeLocations();
            break;
        case "SOC":
        case "BIZ":
            ui.printSocAndBizLocations();
            break;
        case "HOSTEL":
            ui.printHostelLocations();
            break;
        default:
            break;
        }
    }
}
