package seedu.notus.command;

import seedu.notus.data.exception.SystemException;
import seedu.notus.ui.Formatter;

import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

//@@author R-Ramana
/**
 * Archives a Note from the Notebook.
 */
public class ArchiveNoteCommand extends Command {

    public static final String COMMAND_WORD = "archive-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Archives a note. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + "[" + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX]";

    public static final String COMMAND_SUCCESSFUL_MESSAGE = "The following note has been archived: ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "This note does not exist in the notebook! ";
    public static final String INDEX_OUT_OF_RANGE_MESSAGE = "The index you specified is out of range. "
            + "Please check and specify a valid index value.";
    public static final String FILE_WRITE_UNSUCCESSFUL_MESSAGE = "Unable to write to file";

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
                    return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
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

            return Formatter.formatString(COMMAND_SUCCESSFUL_MESSAGE + title);
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        }
    }
}
