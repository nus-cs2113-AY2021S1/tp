package seedu.duke.logic.commands;

import seedu.duke.BusStops;
import seedu.duke.ui.Ui;

public class ResetSearchFreqCommand extends Command {
    @Override
    public void executeCommand() {
        BusStops.resetSearchFrequency();
        Ui.printResetSearchFreqMessage();
    }
}
