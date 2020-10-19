package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.ui.Formatter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static seedu.duke.ui.Formatter.LS;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.SUFFIX_INDEX;

/**
 * Deletes a Note from the Notebook.
 */
public class ArchiveNoteCommand extends Command {

    public static final String COMMAND_WORD = "archive-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Archives or un-archives a note. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + "[" + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX]";

    public static final String ARCHIVE_NOTE_MESSAGE = "The following note has been archived: ";
    public static final String UNARCHIVE_NOTE_MESSAGE = "The following note has been unarchived: ";
    public static final String ARCHIVE_LIST_MESSAGE = "The following notes are in your archives: ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "This note does not exist in the notebook! ";

    private int index;
    private String title = "";
    private ArrayList<Note> archivedNotes = new ArrayList<>();

    /**
     * Constructs a ArchiveNoteCommand to (un)archive a Note.
     *
     * @param index of the item to be (un)archived.
     */
    public ArchiveNoteCommand(int index) {
        this.index = index;
    }

    /**
     * Constructs a ArchiveNoteCommand to (un)archive a Note.
     *
     * @param title of the item to be deleted.
     */
    public ArchiveNoteCommand(String title) {
        this.title = title;
    }

    @Override
    public String execute() {
        boolean isArchived;
        String archivedTitle = "";
        Note archivedNote;
        ArrayList<Note> archivedNotesList = new ArrayList<>();

        try {
            // If there is no title, (un)archive note by index. Else (un)archive by title.
            if (title.isBlank()) {
                archivedNote = notebook.getNotes().get(index);
                archivedNote.toggleArchived();

                if (archivedNote.getIsArchived()) {
                    archivedNotes.add(archivedNote);
                    isArchived = notebook.deleteNote(index);
                    return Formatter.formatString(ARCHIVE_NOTE_MESSAGE + archivedTitle
                            + ARCHIVE_LIST_MESSAGE + getArchiveList(archivedNotes));
                } else {
                    notebook.addNote(archivedNote);
                    archivedNotes.remove(archivedNote);
                    return Formatter.formatString(UNARCHIVE_NOTE_MESSAGE + archivedTitle
                            + ARCHIVE_LIST_MESSAGE + getArchiveList(archivedNotes));
                }
            } else {
                if (notebook.getNote(title)) {

                    archivedNotesList = (ArrayList<Note>) notebook.getNotes().stream()
                            .filter((s) -> s.getTitle().equalsIgnoreCase(title))
                            .collect(Collectors.toList());

                    for (Note note : archivedNotesList) {
                        note.toggleArchived();
                        if (note.getIsArchived()) {
                            archivedNotes.add(note);
                            isArchived = notebook.deleteNote(title);
                            return Formatter.formatString(ARCHIVE_NOTE_MESSAGE + title
                                    + ARCHIVE_LIST_MESSAGE + getArchiveList(archivedNotes));
                        } else {
                            notebook.addNote(note);
                            archivedNotes.remove(note);
                            return Formatter.formatString(UNARCHIVE_NOTE_MESSAGE + title
                                    + ARCHIVE_LIST_MESSAGE + getArchiveList(archivedNotes));
                        }
                    }
                }
                return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
            }
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        }
    }

    private String getArchiveList(ArrayList<Note> note) {
        StringBuilder noteString = new StringBuilder();

        for (int i = 0; i < note.size(); i++) {
            noteString.append(i + 1).append(SUFFIX_INDEX)
                    .append(note.get(i).getTitle())
                    .append(" ")
                    .append(note.get(i).getTagsName())
                    .append(LS);
        }

        return noteString.toString();
    }
}
