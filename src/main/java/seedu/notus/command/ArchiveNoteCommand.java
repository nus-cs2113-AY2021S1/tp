package seedu.notus.command;

import seedu.notus.data.exception.SystemException;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.util.NoSuchElementException;

import static seedu.notus.util.CommandMessage.ARCHIVE_NOTE_SUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.FILE_WRITE_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.INDEX_OUT_OF_RANGE_MESSAGE;
import static seedu.notus.util.CommandMessage.NOTE_DOES_NOT_EXIST_MESSAGE;

//@@author R-Ramana
/**
 * Archives a Note from the Notebook.
 */
public class ArchiveNoteCommand extends Command {

    public static final String COMMAND_WORD = "archive-n";

    private int index;
    private String title = "";

    /**
     * Constructs a ArchiveNoteCommand to archive a Note.
     *
     * @param index of the item to be archived.
     */
    public ArchiveNoteCommand(int index) {
        this.index = index;
    }

    /**
     * Constructs a ArchiveNoteCommand to archive a Note.
     *
     * @param title of the item to be archived.
     */
    public ArchiveNoteCommand(String title) {
        this.title = title;
    }

    @Override
    public String execute() {

        try {
            // If there is no title, archive note by index. Else archive by title.
            if (title.isBlank()) {
                assert index >= 0;
                if (index > notebook.getSize()) {
                    return Formatter.formatString(INDEX_OUT_OF_RANGE_MESSAGE);
                }
                title = notebook.archiveNotes(index);
            } else {
                // archiveNotes(title) returns a boolean, false if no such title exists
                try {
                    notebook.archiveNotes(title);
                } catch (NoSuchElementException e) {
                    return Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
                }
            }

            try {
                storageManager.saveAllNoteDetails(false);
                storageManager.saveAllNoteDetails(true);

                // delete the content file from unarchived notes and add it to archived
                storageManager.deleteNoteContentFile(title, false);
                storageManager.saveNoteContent(notebook.getNote(title, true), true);
            } catch (IOException exception) {
                return Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
            } catch (SystemException exception) {
                return Formatter.formatString(exception.getMessage());
            }

            return Formatter.formatString(ARCHIVE_NOTE_SUCCESSFUL_MESSAGE + title);
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
        }
    }
}
