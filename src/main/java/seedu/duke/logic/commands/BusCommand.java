package seedu.duke.logic.commands;

import seedu.duke.Bus;
import seedu.duke.BusData;
import seedu.duke.exceptions.CustomException;

import java.util.ArrayList;

public class BusCommand extends Command {

    public String busStop;

    public BusCommand(String busStop) {
        this.busStop=busStop.trim();
    }

    @Override
    public void executeCommand() throws CustomException {
        ArrayList<Bus> busList = BusData.busAtStop(busStop);
        System.out.print(busStop + ":  ");
        for (Bus bus: busList) {
            System.out.print(bus.getBusNumber() + "   ");
        }
        System.out.println();

    }
}
