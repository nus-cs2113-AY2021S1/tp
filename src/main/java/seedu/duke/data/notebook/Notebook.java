package seedu.duke.data.notebook;

import java.util.ArrayList;

/**
 * Represents a Notebook object. Contains all the notes.
 */
public class Notebook {

    private ArrayList<Note> notes;
    private ArrayList<Note> archivedNotes;

    /**
     * Creates a new list of notes.
     */
    public Notebook() {
        notes = new ArrayList<>();
        archivedNotes = new ArrayList<>();
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

    public Note getNote(String noteTitle, ArrayList<Note> notebook) {
        return notebook.stream()
                .filter((s) -> s.getTitle().equalsIgnoreCase(noteTitle))
                .findFirst().get();
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

    public String archiveNotes(int index) {
        Note archivedNote = notes.get(index);

        archivedNotes.add(archivedNote);
        deleteNote(index);

        return archivedNote.getTitle();
    }

    public String archiveNotes(String noteTitle) {
        Note archivedNote = getNote(noteTitle, notes);

        archivedNotes.add(archivedNote);
        deleteNote(noteTitle);

        return archivedNote.getTitle();
    }

    public String unarchiveNotes(int index) {
        Note unarchivedNote = archivedNotes.get(index);

        addNote(unarchivedNote);
        archivedNotes.remove(unarchivedNote);

        return unarchivedNote.getTitle();
    }

    public String unarchiveNotes(String noteTitle) {
        Note unarchivedNote = getNote(noteTitle, archivedNotes);

        addNote(unarchivedNote);
        archivedNotes.remove(unarchivedNote);

        return unarchivedNote.getTitle();
    }

    public ArrayList<Note> getArchivedNotes() {
        return archivedNotes;
    }

    public int getSize() {
        return notes.size();
    }
}
