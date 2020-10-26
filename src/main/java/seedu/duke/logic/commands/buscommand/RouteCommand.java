package seedu.duke.logic.commands.buscommand;

import seedu.duke.model.bus.Bus;
import seedu.duke.model.bus.BusData;
import seedu.duke.model.bus.BusStops;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.logic.similaritycheck.SimilarityCheck;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.logic.parser.RouteParser;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.EnumSet;

public class RouteCommand extends Command {

    private String rawMessage;
    private RouteParser parser;

    public RouteCommand(String rawMessage) {
        this.rawMessage = rawMessage;
        parser = new RouteParser(rawMessage);
    }

    @Override
    public void executeCommand() throws CustomException {
        String [] locations = parser.getLocations();
        checkLocations(locations);
        ArrayList<String> possibleLocs = new ArrayList<>(similarLocations(locations));
        if (possibleLocs.isEmpty()) {
            ArrayList<Bus> busOptions = BusData.possibleBuses(locations[0].trim(), locations[1].trim());
            Ui.printRouteMessage(busOptions);
        } else {
            Ui.printPossibleLocsMessage(possibleLocs);
        }
    }

    private void checkLocations(String[] locations) throws CustomException {
        if (locations[0].trim().length() == 0 || locations[1].trim().length() == 0) {
            throw new CustomException(ExceptionType.NO_LOCATIONS);
        } else if (locations[0].trim().equalsIgnoreCase(locations[1].trim())) {
            assert !locations[0].isBlank() : "Location 0 is empty.";
            assert !locations[1].isBlank() : "Location 1 is empty.";
            throw new CustomException(ExceptionType.SAME_LOCATIONS);
        }
        assert !locations[0].isBlank() : "Location 0 is empty.";
        assert !locations[1].isBlank() : "Location 1 is empty.";
        assert !(locations[0].trim().equalsIgnoreCase(locations[1].trim())) : "Seems like the locations are still "
                + "the same.";
    }

    private ArrayList<String> similarLocations(String[] locations) {
        ArrayList<String> possibleLocs = new ArrayList<>();
        ArrayList<String> routeNames = new ArrayList<>();
        for (BusStops info: EnumSet.allOf(BusStops.class)) {
            routeNames.add(info.getName().toLowerCase());
        }
        if (!routeNames.contains(locations[0].trim().toLowerCase())) {
            possibleLocs = SimilarityCheck.similarLoc(locations[0]);
        } else if (!routeNames.contains(locations[1].trim().toLowerCase())) {
            possibleLocs = SimilarityCheck.similarLoc(locations[1]);
        }
        return possibleLocs;
    }

}
