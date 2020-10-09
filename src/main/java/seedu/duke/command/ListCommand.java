package seedu.duke.command;

import seedu.duke.event.EventList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.data.UserData;

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
        this.command = command;
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
        ArrayList<EventList> eventLists = data.getAllEventLists();
        if (command == null) {
            ui.printAvailableList(eventLists);
        } else if (command.equals("All")) {
            for (EventList list : eventLists) {
                ui.printList(list);
            }
        } else {
            ui.printList(data.getEventList(command));
        }
    }

    public static Command parse(String input) {
        if (input.isBlank()) {
            return new ListCommand(null);
        } else {
            input = input.toLowerCase();
            input = input.substring(0, 1).toUpperCase() + input.substring(1);
            return new ListCommand(input);
        }
    }
}
