package seedu.duke.data.notebook;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    /**
     * Gets a list of notes from existing data.
     *
     * @return notes of existing data.
     */
    public ArrayList<Note> getNotes() {
        return this.notes;
    }

    public Note getNote(int index) {
        return notes.get(index);
    }

    public boolean getNote(String noteTitle) {
        return notes.stream().anyMatch(note -> note.getTitle().equalsIgnoreCase(noteTitle));
    }

    /**
     * Adds a note into the notebook.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Removes a note from the notebook.
     */
    public boolean deleteNote(int index) {
        notes.remove(index);
        return true;
    }

    /**
     * Removes a note from the notebook.
     */
    public boolean deleteNote(String title) {
        return notes.removeIf(note -> note.getTitle().equalsIgnoreCase(title));
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public int getSize() {
        return notes.size();
    }
}
