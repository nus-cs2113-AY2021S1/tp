package seedu.duke.logic.commands;

import seedu.duke.BusRoute;
import seedu.duke.exceptions.CustomException;
import seedu.duke.ui.Ui;

public class RouteMapCommand extends Command {

    @Override
    public void executeCommand() {
        try {
            Ui.printRouteSelectionMessage();
            String userBusRouteSelection = Ui.getCommand().trim().toLowerCase();
            System.out.println(BusRoute.selectBusRoute(userBusRouteSelection));
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}
