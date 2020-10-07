package seedu.duke.command;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;

/**
 * Deletes a Note from the Notebook or an Event from the Timetable.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD_NOTE = "delete-n";
    public static final String COMMAND_WORD_EVENT = "delete-e";

    public static final String COMMAND_USAGE_NOTE = COMMAND_WORD_NOTE + ": Deletes a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";
    public static final String COMMAND_USAGE_EVENT = COMMAND_WORD_EVENT + ": Deletes an event. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";

    private int index;
    private boolean isDeleteNote;

    public static String getCommandUsageNote() {
        return COMMAND_USAGE_NOTE;
    }

    public static String getCommandUsageEvent() {
        return COMMAND_USAGE_EVENT;
    }

    /**
     * Constructs a DeleteCommand to delete a Note or an Event.
     *
     * @param index        of the item to be deleted.
     * @param isDeleteNote determines to delete a Note or an Event.
     */
    public DeleteCommand(int index, boolean isDeleteNote) {
        this.index = index;
        this.isDeleteNote = isDeleteNote;
    }

    @Override
    public String execute() {
        if (isDeleteNote) {
            try {

                String title = notebook.getNotes().get(index - 1).getTitle();
                notebook.deleteNote(index - 1);
                return "Note deleted: " + title;
            } catch (IndexOutOfBoundsException | SystemException exception) {
                return "This note does not exists in the notebook";
            }
        } else {
            return "Event deleted";
        }
    }
}
