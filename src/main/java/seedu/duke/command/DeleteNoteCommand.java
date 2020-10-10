package seedu.duke.command;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;

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
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Note deleted: ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "This note does not exists in the notebook! ";
    /*
    public static final String COMMAND_WORD_EVENT = "delete-e"; TBR*/

    public static final String COMMAND_USAGE_NOTE = COMMAND_WORD + ": Deletes a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE or "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";

    /*
    private static final String COMMAND_USAGE_EVENT = COMMAND_WORD_EVENT + ": Deletes an event. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX"; TBR*/

    private int index;
    private String title = "";

    public static String getCommandUsageNote() {
        return COMMAND_USAGE_NOTE;
    }

    /*
    public static String getCommandUsageEvent() {
        return COMMAND_USAGE_EVENT;
    } TBR*/

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
        ArrayList<Note> deletedListTitle;
        try {
            // If there is no title, delete note by index
            if (title.isBlank()) {
                String deletedTitle = notebook.getNotes().get(index - 1).getTitle();
                notebook.deleteNote(index - 1);
                return COMMAND_SUCCESSFUL_MESSAGE + deletedTitle;
            } else {
                deletedListTitle = (ArrayList<Note>) notebook.getNotes().stream()
                        .filter((s) -> s.getTitle().toLowerCase().contains(title.toLowerCase()))
                        .collect(toList());
                notebook.deleteNote(deletedListTitle.get(0).getTitle());
                return COMMAND_SUCCESSFUL_MESSAGE + deletedListTitle.get(0).getTitle();
            }
        } catch (IndexOutOfBoundsException | NullPointerException | ClassCastException exception) {
            return COMMAND_UNSUCCESSFUL_MESSAGE;
        }
    }
}
