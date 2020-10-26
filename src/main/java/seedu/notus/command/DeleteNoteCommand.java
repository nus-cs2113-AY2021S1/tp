package seedu.notus.command;

//@@author Nazryl

import seedu.notus.ui.Formatter;

import seedu.notus.data.exception.SystemException;

import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;

import java.io.IOException;

//@@author Nazryl
/**
 * Deletes a Note from the Notebook.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "delete-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Deletes a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE or "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";

    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Note deleted: ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "This note does not exist in the notebook! ";
    public static final String FILE_WRITE_UNSUCCESSFUL_MESSAGE = "Unable to write to file";

    private int index;
    private String title = "";

    /**
     * Constructs a DeleteNoteCommand to delete a Note.
     *
     * @param index of the item to be deleted.
     */
    public DeleteNoteCommand(int index) {
        this.index = index;
    }

    /**
     * Constructs a DeleteNoteCommand to delete a Note.
     *
     * @param title of the item to be deleted.
     */
    public DeleteNoteCommand(String title) {
        this.title = title;
    }

    @Override
    public String execute() {
        boolean isDeleted;
        String deletedTitle = "";

        try {
            // If there is no title, delete note by index. Else delete by title.
            if (title.isBlank()) {
                assert index >= 0;
                deletedTitle = notebook.getNote(index).getTitle();
                isDeleted = notebook.deleteNote(index);
            } else {
                isDeleted = notebook.deleteNote(title);
            }

            if (isDeleted &&  title.isBlank()) {
                storageManager.deleteNoteContentFile(deletedTitle);
                storageManager.saveAllNoteDetails(notebook);
                return Formatter.formatString(COMMAND_SUCCESSFUL_MESSAGE + deletedTitle);
            } else if (isDeleted) {
                storageManager.deleteNoteContentFile(title);
                storageManager.saveAllNoteDetails(notebook);
                return Formatter.formatString(COMMAND_SUCCESSFUL_MESSAGE + title);
            } else {
                return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
            }
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        } catch (IOException e) {
            return Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
        } catch (SystemException exception) {
            return Formatter.formatString(exception.getMessage());
        }
    }
}
