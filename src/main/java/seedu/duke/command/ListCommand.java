package seedu.duke.command;

import seedu.duke.event.EventList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

/**
 * Command to list events.
 */
public class ListCommand extends Command {
    /**
     * Constructor for listing events seedu.duke
     *
     * @param command from user input
     */
    public ListCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    /*@Override
    public void execute(ArrayList<EventList> eventLists, Ui ui, Storage storage) {
        ui.printList(eventLists);
    }*/
}
