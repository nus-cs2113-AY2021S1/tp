package seedu.duke.data.notebook;

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
     * Removes a note from the timetable.
     */
    public void deleteNote(int index) {
        notes.remove(index);
    }

    /**
     * Removes a note from the timetable.
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
