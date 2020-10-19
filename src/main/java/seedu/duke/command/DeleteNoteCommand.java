package seedu.duke.command;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;
import seedu.duke.ui.Formatter;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;

/**
 * Deletes a Note from the Notebook or an Event from the Timetable.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "delete-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Deletes a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE or "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";

    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Note deleted: ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "This note does not exist in the notebook! ";

    private int index;
    private String title = "";

    /**
     * Constructs a DeleteNoteCommand to delete a Note or an Event.
     *
     * @param index of the item to be deleted.
     */
    public DeleteNoteCommand(int index) {
        this.index = index;
    }

    /**
     * Constructs a DeleteNoteCommand to delete a Note or an Event.
     *
     * @param title of the item to be deleted.
     */
    public DeleteNoteCommand(String title) {
        this.title = title;
    }

    @Override
    public String execute() {
        boolean isDeleted;
        try {
            // If there is no title, delete note by index. Else delete by title.
            if (title.isBlank()) {
                String deletedTitle = notebook.getNotes().get(index).getTitle();
                notebook.deleteNote(index);

                return Formatter.formatString(COMMAND_SUCCESSFUL_MESSAGE + deletedTitle);
            } else {
                isDeleted = notebook.deleteNote(title);

                if (isDeleted) {
                    return Formatter.formatString(COMMAND_SUCCESSFUL_MESSAGE + title);
                } else {
                    return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
                }
            }
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        }
    }
}
