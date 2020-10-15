package seedu.duke.logic.commands;

import seedu.duke.BusStops;
import seedu.duke.exceptions.CustomException;

public class ListStopsCommand extends Command{
    @Override
    public void executeCommand() throws CustomException {
        BusStops.listStops();
    }
}
