package seedu.duke.command;

import seedu.duke.EventLogger;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.InvalidListException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Command to view notes.
 */
public class ViewCommand extends Command {
    private int index;
    private String event;
    private static Logger logger = EventLogger.getEventLogger();

    /**
     * Constructor for viewing.
     *
     * @param command user's command.
     */
    public ViewCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    /**
     * View notes.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     * @throws DukeException catch any exception under Duke.
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        parseUserCommand(command);
        EventList list = data.getEventList(event);
        Event eventRequested = list.getEventByIndex(index - 1);
        if (eventRequested != null) {
            ArrayList<String> existingNotes = eventRequested.getNotes();
            ui.printViewNote(existingNotes);
            logger.fine("View Command executed");
        }

    }

    /**
     * Parsing user command.
     * @param command user input
     * @throws DukeException catch any exception under Duke.
     */
    private void parseUserCommand(String command) throws DukeException {
        command = command.trim();
        String[] commandSplit = command.split(";");
        if (commandSplit.length == 2) {
            try {
                index = parsingNumber(commandSplit[1].trim());
                event = commandSplit[0].trim();
            } catch (NumberFormatException e) {
                logger.warning("WrongNumberFormatException encountered -- View index is not a number");
                throw new WrongNumberFormatException("Index must be numerical format!");
            }
        } else {
            logger.warning("WrongNumberOfArgumentsException encountered "
                    + "-- View have incorrect number of arguments");
            throw new WrongNumberOfArgumentsException("Incorrect number of parameters for View!"
                    + System.lineSeparator()
                    + "The format for View is : \"view <EVENT_TYPE>; <EVENT_INDEX>;\" ");
        }
    }

    /**
     * Check if index is numerical format.
     *
     * @param number index in string format
     * @return index of event
     */
    private int parsingNumber(String number) {
        try {
            int index = Integer.parseInt(number);
            return index;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Index must be numerical format!");
        }
    }
}
