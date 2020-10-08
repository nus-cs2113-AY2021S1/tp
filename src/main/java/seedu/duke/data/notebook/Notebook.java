package seedu.duke.data.notebook;

import seedu.duke.data.exception.SystemException;

import java.util.ArrayList;

/**
 * Represents a Notebook object. Contains all the notes.
 */
public class Notebook {

    private ArrayList<Note> notes;

    /**
     * Creates a new list of notes.
     */
    public Notebook() {
        notes = new ArrayList<>();
    }

    /**
     * Creates a list of notes from existing data.
     *
     * @param notes of existing data.
     */
    public Notebook(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<Note> getNotes() {
        return this.notes;
    }

    public Note getNote(int index){
        return this.notes.get(index);
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    /**
     * Adds a note into the notebook.
     *
     * @throws SystemException if an error occurs.
     */
    public void addNote(Note note) throws SystemException {
        notes.add(note);
    }

    /**
     * Removes a note from the timetable.
     *
     * @throws SystemException if an error occurs.
     */
    public void deleteNote(int note) throws SystemException {
        notes.remove(note);
    }

    public int  getSize(){
        return notes.size();
    }
}
