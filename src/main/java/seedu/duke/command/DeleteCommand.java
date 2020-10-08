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
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD_NOTE = "delete-n";

    public static final String COMMAND_USAGE_NOTE = COMMAND_WORD_NOTE + ": Deletes a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE or "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX ";

    private int index;
    private String title = "";

    /**
     * Constructs a DeleteCommand to delete a Note or an Event.
     *
     * @param index of the item to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Constructs a DeleteCommand to delete a Note or an Event.
     *
     * @param title of the item to be deleted.
     */
    public DeleteCommand(String title) {
        this.title = title;
    }

    @Override
    public String execute() {
        ArrayList<Note> deletedListTitle;
        try {
            if (title.isBlank()) {
                String deletedTitle = notebook.getNotes().get(index - 1).getTitle();
                notebook.deleteNote(index - 1);
                return "Note deleted: " + deletedTitle;
            } else {
                deletedListTitle = (ArrayList<Note>) notebook.getNotes().stream()
                        .filter((s) -> s.getTitle().toLowerCase().contains(title.toLowerCase()))
                        .collect(toList());
                notebook.deleteNote(deletedListTitle.get(0).getTitle());
                return "Note deleted: " + deletedListTitle.get(0).getTitle();
            }
        } catch (IndexOutOfBoundsException | SystemException | NullPointerException | ClassCastException exception) {
            return "This note does not exists in the notebook";
        }
    }
}
