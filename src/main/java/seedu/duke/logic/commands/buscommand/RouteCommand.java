package seedu.duke.logic.commands.buscommand;

import seedu.duke.model.bus.Bus;
import seedu.duke.model.bus.BusData;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.logic.similaritycheck.SimilarityCheck;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.logic.parser.RouteParser;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static seedu.duke.model.bus.BusStops.isValidBusStop;

public class RouteCommand extends Command {

    private String rawMessage;
    private RouteParser parser;

    public RouteCommand(String rawMessage) {
        this.rawMessage = rawMessage;
        parser = new RouteParser(rawMessage);
        super.isValid = false;
    }

    @Override
    public void executeCommand() throws CustomException {
        String [] locations = parser.getLocations();
        checkLocations(locations);
        ArrayList<String> possibleLocs = new ArrayList<>(similarLocations(locations));
        if (possibleLocs.isEmpty()) {
            if (areValidLocations(locations)) {
                ArrayList<Bus> busOptions = BusData.possibleBuses(locations[0].trim(), locations[1].trim());
                Ui.printRouteMessage(busOptions);
                isValid = true;
            }
        } else {
            Ui.printPossibleLocsMessage(possibleLocs);
            throw new CustomException(ExceptionType.POSSIBLE_LOCS_DUMMY);
        }
    }

    private boolean areValidLocations(String[] locations) throws CustomException {
        if (isValidBusStop(locations[0].trim())) {
            if (isValidBusStop(locations[1].trim())) {
                return true;
            } else {
                throw new CustomException(ExceptionType.INVALID_DEST);
            }
        } else {
            if (isValidBusStop(locations[1].trim())) {
                throw new CustomException(ExceptionType.INVALID_START_LOC);
            } else {
                throw new CustomException(ExceptionType.INVALID_LOCATIONS);
            }
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
        if (!isValidBusStop(locations[0].trim())) {
            possibleLocs = SimilarityCheck.similarLoc(locations[0]);
        } else if (!isValidBusStop(locations[1].trim())) {
            possibleLocs = SimilarityCheck.similarLoc(locations[1]);
        }
        return possibleLocs;
    }

}
