package seedu.duke.logic.commands.buscommand;

import seedu.duke.model.bus.Bus;
import seedu.duke.model.bus.BusData;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.ui.Ui;

public class RouteMapCommand extends Command {
    public String busCode;

    public RouteMapCommand(String busCode) {
        this.busCode = busCode.trim();
    }

    @Override
    public void executeCommand() throws CustomException {
        String userBusRouteSelection = this.busCode;
        selectAndPrintBusRoute(userBusRouteSelection);
    }

    private void selectAndPrintBusRoute(String userBusRouteSelection) throws CustomException {
        assert !userBusRouteSelection.isBlank() : "User input is empty.";
        if (userBusRouteSelection.isEmpty()) {
            throw new CustomException(ExceptionType.MISSING_BUS_CODE);
        }
        try {
            Bus selectedBus = BusData.selectBus(userBusRouteSelection);
            Ui.printFullRoute(selectedBus);
        } catch (NullPointerException e) {
            throw new CustomException(ExceptionType.INVALID_BUS);
        }
    }
}

