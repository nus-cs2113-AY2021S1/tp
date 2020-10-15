package seedu.duke.logic.commands;

import seedu.duke.Bus;
import seedu.duke.BusData;
import seedu.duke.ui.Ui;

import static seedu.duke.ui.Ui.printLine;


public class RouteMapCommand extends Command {

    @Override
    public void executeCommand() {
        Ui.printRouteSelectionMessage();
        String userBusRouteSelection = Ui.getCommand().trim().toLowerCase();
        try {
            Bus selectedBus = BusData.selectBus(userBusRouteSelection);
            Ui.printFullRoute(selectedBus);
        } catch (NullPointerException e) {
            System.out.println("The bus code that you have provided is not valid!");
            printLine();
        }
    }
}
