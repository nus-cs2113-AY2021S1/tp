package seedu.duke.logic.commands;

import seedu.duke.Bus;
import seedu.duke.BusData;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.ui.Ui;

public class RouteMapCommand extends Command {
    @Override
    public void executeCommand() throws CustomException {
        Ui.printRouteSelectionMessage();
        String userBusRouteSelection = Ui.getCommand().trim().toLowerCase();
        assert !userBusRouteSelection.isBlank() : "User input is empty.";
        try {
            Bus selectedBus = BusData.selectBus(userBusRouteSelection);
            Ui.printFullRoute(selectedBus);
        } catch (NullPointerException e) {
            throw new CustomException(ExceptionType.INVALID_BUS);
        }
    }
}

