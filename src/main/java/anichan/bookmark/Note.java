package anichan.bookmark;

import java.util.ArrayList;

/**
 * Represents a note for a bookmark entry.
 */
public class Note {

    private ArrayList<String> notes;

    public Note() {
        this.notes = new ArrayList<>();
    }

    /**
     * Add a note into note list.
     *
     * @param note the note to be added
     */
    public void addNote(String note) {
        this.notes.add(note);
    }

    public String getNote(int noteIndex) {
        return notes.get(noteIndex);
    }

    public int getSize() {
        return notes.size();
    }

    /**
     * Remove a note from notes(note list) using note id.
     *
     * @param noteIndex the note index to be remove
     * @return String of the note removed
     */
    public String removeNote(int noteIndex) {
        String note = notes.get(noteIndex);
        this.notes.remove(noteIndex);
        return note;
    }

}
