package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;
import seedu.duke.ui.Formatter;

import java.util.ArrayList;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;

/**
 * Tags or untags a Note.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Tags or untags a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...]";

    public static final String TAG_NOTE_MESSAGE = "Added the tag to the note! ";
    public static final String UNTAG_NOTE_MESSAGE = "Removed the tag from the note! ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "Invalid index input!";
    public static final String TAG_NOTE_HEADER = "Tagging note...";

    private int index;
    private ArrayList<Tag> tags;

    /**
     * Constructs a TagCommand to tag or untag a Note.
     */
    public TagCommand(int index, ArrayList<Tag> tags) {
        this.index = index;
        this.tags = tags;
    }

    @Override
    public String execute() {
        try {
            Note note = notebook.getNotes().get(index);
            ArrayList<String> executedMessage = tagManager.tagAndUntagNote(note, tags, TAG_NOTE_MESSAGE,
                    UNTAG_NOTE_MESSAGE);
            executedMessage.add(0, TAG_NOTE_HEADER);
            return Formatter.formatString(executedMessage, true);
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        }
    }
}
