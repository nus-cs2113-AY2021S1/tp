package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;

/**
 * Tags or untags a Note.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";
    private static final String ADD_TAG_MESSAGE = "Added the tag to the note!";
    private static final String REMOVE_TAG_MESSAGE = "Removed the tag from the note!";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Tags or untags a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG_NAME [TAG_COLOR]";

    private int index;
    private String tagName;
    private String tagColor;

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    public TagCommand(int index, String tagName, String tagColor) {
        this.index = index;
        this.tagName = tagName;
        this.tagColor = tagColor;
    }

    @Override
    public String execute() {
        Tag tag = tagManager.getTag(tagName);

        Note note = notebook.getNotes().get(index - 1);

        // Remove the tag from the note if it does not have the Tag
        if (note.getTags().contains(tag)) {
            tagManager.removeTag(note, tag);
            return REMOVE_TAG_MESSAGE + tag;
        } else {
            tagManager.tagNote(note, tagName, tagColor);
            return ADD_TAG_MESSAGE + tag;
        }
    }
}
