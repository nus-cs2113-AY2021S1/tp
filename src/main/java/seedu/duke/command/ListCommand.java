package seedu.duke.command;

import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
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
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        ArrayList<EventList> eventLists = data.getAllEventLists();
        if (command == null) {
            ui.printAvailableList(eventLists);
        } else if (command.equals("All")) {
            listAll(ui, eventLists);
        } else {
            ui.printList(data.getEventList(command));
        }
    }

    private void listAll(Ui ui, ArrayList<EventList> eventLists) {
        int listNum = eventLists.size();
        for (EventList list : eventLists) {
            ui.printList(list);
            if (listNum > 1) {
                ui.printDividerLine();
            }
            listNum--;
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
