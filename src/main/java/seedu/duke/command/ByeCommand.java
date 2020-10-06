package seedu.duke.command;

import seedu.duke.event.EventList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

/**
 * Command to end program seedu.duke.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for exiting the program.
     */
    public ByeCommand() {
        this.isExit = true;
    }

    /*@Override
    public void execute(ArrayList<EventList> eventLists, Ui ui, Storage storage) {
          ui.printByeMessage();
    }*/
}
