package seedu.eduke8.note;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.common.EditableList;
import seedu.eduke8.exception.Eduke8Exception;

import java.util.ArrayList;
import java.util.logging.Logger;

public class NoteList implements DisplayableList, EditableList {
    private ArrayList<Displayable> notes;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static String INVALID_INT = "Invalid index";

    public NoteList(ArrayList<Displayable> notes) {
        this.notes = notes;
    }

    /**
     * Adds a Displayable object into the NoteList.
     *
     * @param note A note, which is an object that implements the Displayable interface.
     */
    @Override
    public void add(Displayable note) {
        notes.add(note);
    }

    /**
     * Deletes a Displayable object (specified with the provided int index) from the NoteList.
     *
     * @param index An index, which indicates which note in the NoteList to is to be deleted.
     * @return Displayable notes.remove(index).
     */
    @Override
    public Displayable delete(int index) {
        assert (index > 0 && index < notes.size());
        return notes.remove(index);
    }

    /**
     * Returns all of the notes in the NoteList object.
     *
     * @return Note objects in the NoteList object.
     */
    @Override
    public ArrayList<Displayable> getInnerList() {
        return notes;
    }

    /**
     * Finds a Displayable object that has a description that matches the description provided in the
     * input.
     *
     * @param input A String input, taken from the User's input.
     * @return Note object in the NoteList that matches the description provided in input.
     */
    @Override
    public Displayable find(String input) {
        for (Displayable note : notes) {
            if (input.equalsIgnoreCase(note.getDescription())) {
                return note;
            }
        }
        return null;
    }

    /**
     * Returns the Note that has the provided index, in their respective NoteLists.
     *
     * @param index is used to identify the Note object to be fetched.
     * @return Note object in the NoteList that matches the index.
     */
    public Note get(int index) {
        return (Note) notes.get(index);
    }

    /**
     * Returns the total number of Note objects in a NoteList object.
     *
     * @return int size of the NoteList.
     */
    public int getCount() {
        return notes.size();
    }
}
