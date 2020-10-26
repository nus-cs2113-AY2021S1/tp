package seedu.duke.logic.commands.buscommand;

import seedu.duke.model.bus.BusStops;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.ui.Ui;

public class ResetSearchFreqCommand extends Command {
    @Override
    public void executeCommand() {
        BusStops.resetSearchFrequency();
        Ui.printResetSearchFreqMessage();
    }
}
