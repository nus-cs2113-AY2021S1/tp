package seedu.duke.command;

import seedu.duke.EventLogger;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.data.UserData;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Command to list events.
 */
public class ListCommand extends Command {
    private static Logger logger = EventLogger.getEventLogger();

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
        logger.fine("List command finished running successfully.");
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

    /**
     * Static parser for list command creation.
     *
     * @param input user input.
     * @return ListCommand with input formatted.
     */
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
