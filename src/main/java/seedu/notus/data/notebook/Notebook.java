package seedu.notus.data.notebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

//@@author R-Ramana
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
     * @param notes ArrayList of Note consisting of existing data.
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

    /**
     * Checks if any note in the notebook is pinned.
     * Method exits the moment there is a pinned note.
     *
     * @return boolean value - true if any note is pinned and false otherwise.
     */
    public boolean checkPinned() {
        for (Note note : notes) {
            if (note.getPinned()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all the pinned notes from the notebook.
     * Adds all the notes that are pinned to another ArrayList.
     *
     * @return noteList ArrayList containing all the pinned notes.
     */
    public ArrayList<Note> getPinnedNotes() {
        ArrayList<Note> noteList = new ArrayList<>();
        for (Note note : notes) {
            if (note.getPinned()) {
                noteList.add(note);
            }
        }
        return noteList;
    }

    /**
     * Gets all the pinned notes from the specified notebook.
     * Adds all the notes that are pinned to another ArrayList.
     *
     * @param noteArrayList ArrayList to be filtered.
     * @return noteList ArrayList containing all the pinned notes.
     */
    public ArrayList<Note> getPinnedNotes(ArrayList<Note> noteArrayList) {
        ArrayList<Note> noteList = new ArrayList<>();
        for (Note note : noteArrayList) {
            if (note.getPinned()) {
                noteList.add(note);
            }
        }
        return noteList;
    }

    /**
     * Gets all the unpinned notes from the notebook.
     * Adds all the notes that are unpinned to another ArrayList.
     *
     * @return noteList ArrayList containing all the unpinned notes.
     */
    public ArrayList<Note> getUnpinnedNotes() {
        ArrayList<Note> noteList = new ArrayList<>();
        for (Note note : notes) {
            if (!note.getPinned()) {
                noteList.add(note);
            }
        }
        return noteList;
    }

    /**
     * Gets all the unpinned notes from the specified notebook.
     * Adds all the notes that are unpinned to another ArrayList.
     *
     * @param noteArrayList ArrayList to be filtered.
     * @return noteList ArrayList containing all the unpinned notes.
     */
    public ArrayList<Note> getUnpinnedNotes(ArrayList<Note> noteArrayList) {
        ArrayList<Note> noteList = new ArrayList<>();
        for (Note note : noteArrayList) {
            if (!note.getPinned()) {
                noteList.add(note);
            }
        }
        return noteList;
    }

    /**
     * Sorts the notebook alphabetically (a-z).
     * Depending on the isAscending order boolean value, sort order can be reversed (z-a).
     * Pinned notes and unpinned notes (if they exist) will be sorted separately.
     * Returns an arrayList of the sorted notes.
     *
     * @param isAscendingOrder boolean specifies if the notes will be sorted a-z or z-a (in reverse order)
     * @param isPinned Boolean specifies what kind of notes to be filtered (acts as a toggle).
     *                 Null means no pinned notes exist and so all the notes will be sorted.
     *                 true means only the pinned notes will be sorted.
     *                 false means only the unpinned notes will be sorted.
     * @return sortedNotes ArrayList containing the sorted notes.
     */
    public ArrayList<Note> getSortedList(Boolean isAscendingOrder, Boolean isPinned) {
        ArrayList<Note> sortedNotes;

        if (isPinned == null) {
            // Takes the notes in the notebook and sorts them according to title, alphabetically (a-z)
            sortedNotes = (ArrayList<Note>) notes.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                    .collect(Collectors.toList());
        } else if (!isPinned) {
            // Takes only the unpinned notes in the notebook and sorts them according to title, alphabetically (a-z)
            sortedNotes = (ArrayList<Note>) notes.stream()
                    .filter(Objects::nonNull)
                    .filter((s) -> !s.getPinned())
                    .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            // Takes only the pinned notes in the notebook and sorts them according to title, alphabetically (a-z)
            sortedNotes = (ArrayList<Note>) notes.stream()
                    .filter(Objects::nonNull)
                    .filter(Note::getPinned)
                    .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (!isAscendingOrder) {
            Collections.reverse(sortedNotes);
        }

        return sortedNotes;
    }

    /**
     * Sorts the specified notebook alphabetically (a-z).
     * Depending on the isAscending order boolean value, sort order can be reversed (z-a).
     * Pinned notes and unpinned notes (if they exist) will be sorted separately.
     * Returns an arrayList of the sorted notes.
     *
     * @param noteArrayList ArrayList of notes that is required to be sorted.
     * @param isAscendingOrder boolean specifies if the notes will be sorted a-z or z-a (in reverse order)
     * @param isPinned Boolean specifies what kind of notes to be filtered (acts as a toggle).
     *                 Null means no pinned notes exist and so all the notes will be sorted.
     *                 true means only the pinned notes will be sorted.
     *                 false means only the unpinned notes will be sorted.
     * @return sortedNotes ArrayList containing the sorted notes.
     */
    public ArrayList<Note> getSortedList(Boolean isAscendingOrder, Boolean isPinned, ArrayList<Note> noteArrayList) {
        ArrayList<Note> sortedNotes;

        if (isPinned == null) {
            // Takes the notes in the notebook and sorts them according to title, alphabetically (a-z)
            sortedNotes = (ArrayList<Note>) noteArrayList.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                    .collect(Collectors.toList());
        } else if (isPinned) {
            // Takes only the pinned notes in the notebook and sorts them according to title, alphabetically (a-z)
            sortedNotes = (ArrayList<Note>) noteArrayList.stream()
                    .filter(Objects::nonNull)
                    .filter(Note::getPinned)
                    .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            // Takes only the unpinned notes in the notebook and sorts them according to title, alphabetically (a-z)
            sortedNotes = (ArrayList<Note>) noteArrayList.stream()
                    .filter(Objects::nonNull)
                    .filter((s) -> !s.getPinned())
                    .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (!isAscendingOrder) {
            Collections.reverse(sortedNotes);
        }

        return sortedNotes;
    }

    public Note getNote(int index) {
        return notes.get(index);
    }

    /**
     * Filters the specified notebook to get a note that has a title matching to the noteTitle parameter.
     *
     * @param noteTitle String containing the title of the note
     * @param isArchive boolean specifies what kind of notebook to be used for filtering (acts as a toggle).
     *                  If true, uses the archive notebook. Else it uses the notes notebook.
     * @return note that was filtered.
     */
    public Note getNote(String noteTitle, boolean isArchive) {
        if (isArchive) {
            return notes.stream()
                    .filter((s) -> s.getTitle().equalsIgnoreCase(noteTitle))
                    .findFirst().get();
        }

        return archivedNotes.stream()
                .filter((s) -> s.getTitle().equalsIgnoreCase(noteTitle))
                .findFirst().get();
    }

    /**
     * Returns true if there is a matching note based on the note title parameter.
     */
    public boolean getNote(String noteTitle) {
        return notes.stream().anyMatch(note -> note.getTitle().equalsIgnoreCase(noteTitle));
    }

    /**
     * Adds a note into the notebook.
     */
    public void addNote(Note note) {
        if (note.getIsArchived()) {
            archivedNotes.add(note);
        } else {
            notes.add(note);
        }
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

    /**
     * Replace a note from the notebook.
     */
    public void setNotes(int index, Note note) {
        notes.set(index, note);
    }

    /**
     * Archives the specified note based on the index value.
     * Archiving is done by removing the note from notes and adding it to a separate archivedNotes notebook.
     *
     * @param index integer value of the note to be archived
     * @return String value of the note title.
     */
    public String archiveNotes(int index) {
        Note archivedNote = notes.get(index);

        archivedNotes.add(archivedNote);
        archivedNote.toggleArchived();
        notes.remove(index);

        return archivedNote.getTitle();
    }

    /**
     * Archives the specified note based on the index value.
     * Archiving is done by removing the note from notes and adding it to a separate archivedNotes notebook.
     *
     * @param noteTitle String containing the title of the note
     * @return isDeleted boolean value (true if a note containing the user inputted title exists, false otherwise).
     */
    public boolean archiveNotes(String noteTitle) {
        boolean isDeleted;

        Note archivedNote = getNote(noteTitle, true);
        // true if title exist and is removed from notebook
        isDeleted = deleteNote(noteTitle);

        if (isDeleted) {
            archivedNotes.add(archivedNote);
            archivedNote.toggleArchived();
        }

        return isDeleted;
    }

    /**
     * Un-archives the specified note based on the index value.
     * Un-archiving is done by removing the note from archivedNotes and adding it to notes.
     *
     * @param index integer value of the note to be un-archived
     * @return String value of the note title.
     */
    public String unarchiveNotes(int index) {
        Note unarchivedNote = archivedNotes.get(index);

        notes.add(unarchivedNote);
        unarchivedNote.toggleArchived();
        archivedNotes.remove(unarchivedNote);

        return unarchivedNote.getTitle();
    }

    /**
     * Un-archives the specified note based on the index value.
     * Un-archiving is done by removing the note from archivedNotes and adding it to notes.
     *
     * @param noteTitle String containing the title of the note
     * @return isDeleted boolean value (true if a note containing the user inputted title exists, false otherwise).
     */
    public boolean unarchiveNotes(String noteTitle) {
        boolean isDeleted;

        Note unarchivedNote = getNote(noteTitle, false);
        // true if title exist and is removed from notebook
        isDeleted = archivedNotes.remove(unarchivedNote);

        if (isDeleted) {
            notes.add(unarchivedNote);
            unarchivedNote.toggleArchived();
        }

        return isDeleted;
    }

    public ArrayList<Note> getArchivedNotes() {
        return archivedNotes;
    }

    public ArrayList<Note> findNotes(String keywords) {
        return (ArrayList<Note>) notes.stream()
                .filter((s) -> s.getTitle().toLowerCase().contains(keywords.toLowerCase()))
                .collect(Collectors.toList());
    }

    public int getSize() {
        return notes.size();
    }

    public int getArchivedNoteSize() {
        return archivedNotes.size();
    }
}
