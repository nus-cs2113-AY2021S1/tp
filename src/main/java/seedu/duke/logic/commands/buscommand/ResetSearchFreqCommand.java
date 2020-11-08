package seedu.duke.logic.commands.buscommand;

import seedu.duke.model.bus.BusStops;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.ui.Ui;

/**
 * Represents the command to reset search frequencies of all bus stops.
 */
public class ResetSearchFreqCommand extends Command {

    public ResetSearchFreqCommand() {
        super.isValid = true;
    }

    @Override
    public void executeCommand() {
        BusStops.resetSearchFrequency();
        Ui.printResetSearchFreqMessage();
    }
}
