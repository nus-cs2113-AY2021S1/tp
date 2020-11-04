package seedu.duke.command;

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

/**
 * Command to view notes.
 */
public class ViewCommand extends Command {
    private int index;
    private String event;

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
        try {
            parseUserCommand(command);
            EventList list = data.getEventList(event);
            Event eventRequested = list.getEventByIndex(index - 1);
            if (eventRequested != null) {
                ArrayList<String> existingNotes = eventRequested.getNotes();
                ui.printViewNote(existingNotes);
            } else {
                throw new InvalidListException("No such event type");
            }
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException("Error, no such index is available!");
        }
    }

    private void parseUserCommand(String command) throws DukeException {
        command = command.trim();
        String[] commandSplit = command.split(";");
        if (commandSplit.length == 2) {
            try {
                index = parsingNumber(commandSplit[1].trim());
                event = commandSplit[0].trim();
            } catch (NumberFormatException e) {
                throw new WrongNumberFormatException("Index must be numerical format!");
            }
        } else {
            throw new WrongNumberOfArgumentsException("Incorrect number of parameters for Note!");
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
