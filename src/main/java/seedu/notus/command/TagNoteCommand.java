package seedu.notus.command;

import seedu.notus.data.notebook.Note;
import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;

//@@author Chongjx
/**
 * Tags or untags a Note.
 */
public class TagNoteCommand extends Command {

    public static final String COMMAND_WORD = "tag-n";

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
    public TagNoteCommand(int index, ArrayList<Tag> tags) {
        this.index = index;
        this.tags = tags;
    }

    @Override
    public String execute() {
        try {
            assert index >= 0;
            Note note = notebook.getNotes().get(index);
            ArrayList<String> executedMessage = tagManager.tagAndUntag(note, tags, TAG_NOTE_MESSAGE,
                    UNTAG_NOTE_MESSAGE);
            executedMessage.add(0, TAG_NOTE_HEADER);

            // save the changed details
            try {
                storageManager.saveAllNoteDetails(false);
            } catch (IOException e) {
                return Formatter.formatString(e.getMessage());
            }

            return Formatter.formatString(executedMessage, true);
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        }
    }
}
