package seedu.duke.command;


import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.*;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteCommand extends Command {
    private int index;
    private String event;
    private Scanner sc;
    public NoteCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        try {
            parseUserCommand(command);
            EventList list = data.getEventList(event);
            Event eventRequested = list.getEventByIndex(index-1);
            if (eventRequested != null){
                ArrayList<String>  existingNotes = eventRequested.getNotes();
                ArrayList<String>  additionalNotes = getNotesFromUser();
                ArrayList<String>  updatedNotes = updatingNotesWithTimestamp(existingNotes,additionalNotes);
                eventRequested.setNotes(updatedNotes);
                ui.printNoteMessage(eventRequested,updatedNotes);
            }
            else{
                throw new InvalidListException("No such event type");
            }
            storage.saveFile(storage.getFileLocation(list.getName()),data, list.getName());
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
        }
        else{
            throw new WrongNumberOfArgumentsException("Incorrect number of parameters for Note!");
        }
    }

    private ArrayList<String> getNotesFromUser(){

        sc = new Scanner(System.in);
        ArrayList<String> notesList = new ArrayList<String>();
        String temp = sc.nextLine().trim();
        do{
            notesList.add(temp);
            temp = sc.nextLine().trim();
        }while(!temp.equals(";"));

        return notesList;
    }

    private ArrayList<String> updatingNotesWithTimestamp(ArrayList<String> existingNotes, ArrayList<String> additionalNotes){
        LocalDateTime now = LocalDateTime.now();
        String timestamp = "---------"+now+"---------";
        existingNotes.add(timestamp);
        existingNotes.addAll(additionalNotes);
        return existingNotes;
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