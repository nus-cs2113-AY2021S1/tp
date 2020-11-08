package seedu.duke.logic.commands.buscommand;

import seedu.duke.exceptions.ExceptionType;
import seedu.duke.model.bus.Bus;
import seedu.duke.model.bus.BusData;
import seedu.duke.model.bus.BusStops;
import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.similaritycheck.SimilarityCheck;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Represents the bus command which shows the users the buses available at a specific bus stop.
 */
public class BusCommand extends Command {

    private String busStopName;
    private BusStops busStop;

    /**
     * Initializes BusCommand object.
     *
     * @param busStopName busStopName parameter input by user for bus command
     * @throws CustomException if possible locations detected
     */
    public BusCommand(String busStopName) throws CustomException {
        ArrayList<String> possibleLocs = new ArrayList<>(similarLocations(busStopName));
        if (possibleLocs.isEmpty()) {
            setBusStop(busStopName);
            super.isValid = true;
        } else {
            Ui.printPossibleLocsMessage(possibleLocs);
            super.isValid = false;
            throw new CustomException(ExceptionType.POSSIBLE_LOCS_DUMMY);
        }
    }

    /**
     * Checks if busStop parameter input by user exists in the list of bus stops at NUS and sets busStop.
     *
     * @param busStopName the busStop parameter input by the user
     * @throws CustomException if bus stop does not exist
     */
    private void setBusStop(String busStopName) throws CustomException {
        assert ! (busStopName == null) : "busStopName not declared";
        busStop = BusStops.findBusStop(busStopName.trim());
        if (busStop == null) {
            throw new CustomException(ExceptionType.INVALID_BUS_STOP);
        }
        this.busStopName = busStop.getName();
        busStop.incrementSearchCount();


    }

    @Override
    public void executeCommand() {
        ArrayList<Bus> busList = BusData.getBusAtStop(busStopName);
        Ui.printBusAtBusStop(busList, busStopName);
    }

    /**
     * Returns the list of locations similar to the given locations provided they are not exactly the same as any bus
     * stop in NUS.
     *
     * @param location the locations given by the user after parsing.
     * @return the list of possible locations.
     */
    private ArrayList<String> similarLocations(String location) {
        ArrayList<String> possibleLocs = new ArrayList<>();
        ArrayList<String> routeNames = new ArrayList<>();
        for (BusStops info: EnumSet.allOf(BusStops.class)) {
            routeNames.add(info.getName().toLowerCase());
        }
        if (!routeNames.contains(location.trim().toLowerCase())) {
            possibleLocs = SimilarityCheck.similarLoc(location);
        }
        return possibleLocs;
    }
}
