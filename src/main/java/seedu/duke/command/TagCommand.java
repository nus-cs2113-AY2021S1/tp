package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;

/**
 * Tags or untags a Note.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";
    private final String ADD_TAG_MESSAGE = "Added the tag to the note!";
    private static final String REMOVE_TAG_MESSAGE = "Removed the tag from the note!";

    private Note note;
    private String tagName;
    private String tagColor;

    public TagCommand(Note note, String tagName, String tagColor) {
        this.note = note;
        this.tagName = tagName;
        this.tagColor = tagColor;
    }

    @Override
    public String execute() {
        Tag tag = tagManager.getTag(tagName);

        // Remove the tag from the note if it does not have the Tag
        if (note.getTags().contains(tag)) {
            tagManager.removeTag(note, tag);
            return ADD_TAG_MESSAGE + tag;
        } else {
            tagManager.tagNote(note, tagName, tagColor);
            return REMOVE_TAG_MESSAGE + tag;
        }
    }
}
