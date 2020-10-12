package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;

import java.util.ArrayList;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;


/**
 * Views a specific Note in the Notebook.
 */
public class ViewNoteCommand extends Command {

    public static final String COMMAND_WORD = "view-n";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Views a note. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX] "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE]";

    private static final String COMMAND_UNSUCCESSFUL_MESSAGE = "This note does not exists in the notebook";

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    private int index;
    private String title;
    private boolean isViewByIndex;

    /**
     * Constructs a ViewNoteCommand to view a Note by the index.
     *
     * @param index of the Note.
     */
    public ViewNoteCommand(int index) {
        this.index = index;
        this.title = null;
        this.isViewByIndex = true;
    }

    /**
     * Constructs a ViewNoteCommand to view a Note by the title.
     *
     * @param title of the Note.
     */
    public ViewNoteCommand(String title) {
        this.title = title;
        this.isViewByIndex = false;
    }

    @Override
    public String execute() {
        Note note = new Note("", "", false);
        boolean noteExists = false;
        if (isViewByIndex) {
            try {
                note = notebook.getNotes().get(index);
            } catch (IndexOutOfBoundsException exception) {
                return COMMAND_UNSUCCESSFUL_MESSAGE;
            }
            noteExists = true;
        } else {
            for (Note notes : notebook.getNotes()) {
                if (notes.getTitle().equals(title)) {
                    note = notes;
                    noteExists = true;
                }
            }
        }
        if (!noteExists) {
            return COMMAND_UNSUCCESSFUL_MESSAGE;
        }
        ArrayList<Tag> notetags = note.getTags();

        // format output string
        String stringToPrint = note.getTitle() + "\n";
        for (Tag tag:notetags) {
            stringToPrint += tag.toString() + "\n";
        }

        stringToPrint += note.getContent();
        return stringToPrint;
    }
}
