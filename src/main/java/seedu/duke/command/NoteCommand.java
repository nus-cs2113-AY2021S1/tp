package seedu.duke.command;


import seedu.duke.EventLogger;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Command to make notes.
 */
public class NoteCommand extends Command {
    private int index;
    private String event;
    private static Logger logger = EventLogger.getEventLogger();

    /**
     * Constructor for note.
     *
     * @param command user's command.
     */
    public NoteCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    /**
     * Create and save notes.
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
            ui.printMessage("Please type in your notes."
                    + " To stop note taking, ensure that you are in a new line"
                    + " and type 'noteend', and press enter");
            ArrayList<String> existingNotes = eventRequested.getNotes();
            ArrayList<String> additionalNotes = getNotesFromUser(ui);
            ArrayList<String> updatedNotes = updatingNotesWithTimestamp(existingNotes, additionalNotes);
            eventRequested.setNotes(updatedNotes);
            ui.printNoteMessage(eventRequested, updatedNotes);
        }

        storage.saveFile(storage.getFileLocation(list.getName()), data, list.getName());
        logger.fine("Note for event was created/updated successfully.");
    }

    /**
     * Parse user command.
     *
     * @param command user's command.
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
                logger.warning("WrongNumberFormatException encountered -- Note index is not a number");
                throw new WrongNumberFormatException("Index must be numerical format!");
            }
        } else {
            logger.warning("WrongNumberOfArgumentsException encountered "
                    + "-- Note have incorrect number of arguments");
            throw new WrongNumberOfArgumentsException("Incorrect number of parameters for Note!");
        }
    }

    /**
     * Require user's input for notes.
     *
     * @return a list of notes.
     */
    private ArrayList<String> getNotesFromUser(Ui ui) {

        ArrayList<String> notesList = new ArrayList<String>();
        String temp = ui.receiveCommand().trim();
        while (!temp.equals("noteend")) {
            notesList.add(temp);
            temp = ui.receiveCommand().trim();
        }
        return notesList;
    }

    /**
     * Concatenate existing and new notes with timestamp.
     *
     * @param existingNotes   notes stored in storage.
     * @param additionalNotes notes that are just inputted.
     * @return a combined list of notes.
     */
    private ArrayList<String> updatingNotesWithTimestamp(ArrayList<String> existingNotes,
                                                         ArrayList<String> additionalNotes) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = "---------" + now + "---------";
        existingNotes.add(timestamp);
        additionalNotes = indicateNewLineUsingDelimeter(additionalNotes);
        existingNotes.addAll(additionalNotes);
        return existingNotes;
    }

    private ArrayList<String> indicateNewLineUsingDelimeter(ArrayList<String> notes) {
        ArrayList<String> convertedList = new ArrayList<>();

        for (String note : notes) { //after splitting up the lines based on `, add them
            String[] lines = note.split("`", -1);
            List<String> toBeAdded = Arrays.asList(lines);
            convertedList.addAll(toBeAdded);
        }
        return convertedList;
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