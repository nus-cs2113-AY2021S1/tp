package seedu.duke.logic.commands;

import seedu.duke.Bus;
import seedu.duke.BusData;
import seedu.duke.logic.parser.RouteParser;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class RouteCommand extends Command {

    private String rawMessage;
    private RouteParser parser;

    public RouteCommand(String rawMessage) {
        this.rawMessage = rawMessage;
        parser = new RouteParser(rawMessage);
    }

    @Override
    public void executeCommand() {
        String [] locations = parser.getLocations();
        ArrayList<Bus> busOptions = BusData.possibleBuses(locations[0].trim(), locations[1].trim());
        Ui.printRouteMessage(busOptions);
    }

}
