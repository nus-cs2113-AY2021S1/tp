package seedu.duke.logic.commands;

import seedu.duke.Bus;
import seedu.duke.BusData;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
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
    public void executeCommand() throws CustomException {
        String [] locations = parser.getLocations();
        checkLocations(locations);
        ArrayList<Bus> busOptions = BusData.possibleBuses(locations[0].trim(), locations[1].trim());
        Ui.printRouteMessage(busOptions);
    }

    private void checkLocations(String[] locations) throws CustomException {
        if (locations[0].trim().length() == 0 || locations[1].trim().length() == 0) {
            throw new CustomException(ExceptionType.NO_LOCATIONS);
        } else if (locations[0].trim().equalsIgnoreCase(locations[1].trim())) {
            assert locations[0].trim().length() != 0 : "Location 0 is empty.";
            assert locations[1].trim().length() != 0 : "Location 1 is empty.";
            throw new CustomException(ExceptionType.SAME_LOCATIONS);
        }
        assert locations[0].trim().length() != 0 : "Location 0 is empty.";
        assert locations[1].trim().length() != 0 : "Location 1 is empty.";
        assert !(locations[0].trim().equalsIgnoreCase(locations[1].trim())) : "Seems like the locations are still "
                + "the same.";
    }

}
