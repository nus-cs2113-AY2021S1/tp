package seedu.duke.logic.commands;

import seedu.duke.Bus;
import seedu.duke.BusData;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class AllBusCommand extends Command {

    @Override
    public void executeCommand() {
        ArrayList<Bus> allBuses = BusData.listOfAllBuses();
        Ui.printAllBusMessage(allBuses);
    }

}
