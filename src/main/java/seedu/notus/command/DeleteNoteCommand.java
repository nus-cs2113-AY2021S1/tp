package seedu.notus.command;

//@@author Nazryl

import seedu.notus.data.exception.SystemException;
import seedu.notus.ui.Formatter;

import java.io.IOException;

import static seedu.notus.util.CommandMessage.DELETE_NOTE_SUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.FILE_WRITE_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.NOTE_DOES_NOT_EXIST_MESSAGE;

//@@author Nazryl
/**
 * Deletes a Note from the Notebook.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "delete-n";

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
                storageManager.deleteNoteContentFile(deletedTitle, false);
                storageManager.saveAllNoteDetails(false);
                return Formatter.formatString(DELETE_NOTE_SUCCESSFUL_MESSAGE + deletedTitle);
            } else if (isDeleted) {
                storageManager.deleteNoteContentFile(title, false);
                storageManager.saveAllNoteDetails(false);
                return Formatter.formatString(DELETE_NOTE_SUCCESSFUL_MESSAGE + title);
            } else {
                return Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
            }
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
        } catch (IOException e) {
            return Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
        } catch (SystemException exception) {
            return Formatter.formatString(exception.getMessage());
        }
    }
}
