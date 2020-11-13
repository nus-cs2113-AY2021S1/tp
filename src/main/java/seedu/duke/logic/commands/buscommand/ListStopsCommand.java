package seedu.duke.logic.commands.buscommand;

import seedu.duke.model.bus.BusStops;
import seedu.duke.exceptions.CustomException;
import seedu.duke.logic.commands.commons.Command;

public class ListStopsCommand extends Command {
    public ListStopsCommand() {
        super.isValid = true;
    }

    @Override
    public void executeCommand() throws CustomException {
        BusStops.listStops();
    }
}
