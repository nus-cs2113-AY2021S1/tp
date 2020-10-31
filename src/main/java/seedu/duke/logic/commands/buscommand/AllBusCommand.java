package seedu.duke.logic.commands.buscommand;

import seedu.duke.model.bus.Bus;
import seedu.duke.model.bus.BusData;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class AllBusCommand extends Command {

    public AllBusCommand() {
        super.isValid = true;
    }

    @Override
    public void executeCommand() {
        ArrayList<Bus> allBuses = BusData.listOfAllBuses();
        Ui.printAllBusMessage(allBuses);
    }

}
