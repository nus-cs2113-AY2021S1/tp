package seedu.notus.command;

import seedu.notus.data.exception.SystemException;
import seedu.notus.ui.Formatter;

import static seedu.notus.util.CommandMessage.FILE_WRITE_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.INDEX_OUT_OF_RANGE_MESSAGE;
import static seedu.notus.util.CommandMessage.NOTE_DOES_NOT_EXIST_MESSAGE;
import static seedu.notus.util.CommandMessage.UNARCHIVE_NOTE_SUCCESSFUL_MESSAGE;

import java.io.IOException;
import java.util.NoSuchElementException;

//@@author R-Ramana
/**
 * Un-archives a Note from the Notebook.
 */
public class UnarchiveNoteCommand extends Command {

    public static final String COMMAND_WORD = "unarchive-n";

    private int index;
    private String title = "";

    /**
     * Constructs a UnarchiveNoteCommand to un-archive a Note.
     *
     * @param index of the item to be un-archived.
     */
    public UnarchiveNoteCommand(int index) {
        this.index = index;
    }

    /**
     * Constructs a UnarchiveNoteCommand to un-archive a Note.
     *
     * @param title of the item to be un-archived.
     */
    public UnarchiveNoteCommand(String title) {
        this.title = title;
    }

    @Override
    public String execute() {

        try {
            // If there is no title, un-archive note by index. Else un-archive by title.
            if (title.isBlank()) {
                assert index >= 0;
                if (index > notebook.getArchivedNoteSize()) {
                    return Formatter.formatString(INDEX_OUT_OF_RANGE_MESSAGE);
                }
                title = notebook.unarchiveNotes(index);
            } else {
                // unarchiveNotes(title) returns a boolean, false if no such title exists
                try {
                    notebook.unarchiveNotes(title);
                } catch (NoSuchElementException e) {
                    return Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
                }
            }

            try {
                storageManager.saveAllNoteDetails(true);
                storageManager.saveAllNoteDetails(false);

                // delete the content file from unarchived notes and add it to archived
                storageManager.deleteNoteContentFile(title, true);
                storageManager.saveNoteContent(notebook.getNote(title, false), false);

            } catch (IOException exception) {
                return Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
            } catch (SystemException exception) {
                return Formatter.formatString(exception.getMessage());
            }

            return Formatter.formatString(UNARCHIVE_NOTE_SUCCESSFUL_MESSAGE + title);
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
        }
    }
}
