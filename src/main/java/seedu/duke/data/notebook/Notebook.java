package seedu.duke.data.notebook;

import java.util.ArrayList;

/**
 * Represents a Notebook object. Contains all the notes.
 */
public class Notebook {

    private final ArrayList<Note> notes;

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

    /**
     * Adds a note into the notebook.
     *
     * @param note to be added.
     */
    public void addNote(Note note) {

    }

    /**
     * Removes a note from the timetable.
     *
     * @param note to be removed.
     */
    public void deleteNote(Note note) {

    }
}
