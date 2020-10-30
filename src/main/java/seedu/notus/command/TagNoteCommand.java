package seedu.notus.command;

import seedu.notus.data.notebook.Note;
import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.notus.util.CommandMessage.INDEX_OUT_OF_RANGE_MESSAGE;
import static seedu.notus.util.CommandMessage.TAG_HEADER;
import static seedu.notus.util.CommandMessage.TAG_MESSAGE;
import static seedu.notus.util.CommandMessage.UNTAG_MESSAGE;

//@@author Chongjx
/**
 * Tags or untags a Note.
 */
public class TagNoteCommand extends Command {

    public static final String COMMAND_WORD = "tag-n";

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
            ArrayList<String> executedMessage = tagManager.tagAndUntag(note, tags, TAG_MESSAGE,
                    UNTAG_MESSAGE);
            executedMessage.add(0, TAG_HEADER);

            // save the changed details
            try {
                storageManager.saveAllNoteDetails(false);
            } catch (IOException e) {
                return Formatter.formatString(e.getMessage());
            }

            return Formatter.formatString(executedMessage, true);
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(INDEX_OUT_OF_RANGE_MESSAGE);
        }
    }
}
