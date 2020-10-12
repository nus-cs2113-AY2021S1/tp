package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;
import seedu.duke.ui.InterfaceManager;

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

    private static final String ADD_TAG_MESSAGE = "Added the tag to the note! ";
    private static final String REMOVE_TAG_MESSAGE = "Removed the tag from the note! ";
    private static final String COMMAND_UNSUCCESSFUL_MESSAGE = "Invalid index input!";

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
        String executeMessage = "";

        try {
            Note note = notebook.getNotes().get(index);
            for (Tag t : tags) {
                // Tries to get the tag from the database
                Tag existingTag = tagManager.getTag(t.getTagName());

                // Check if the note contains such tag
                if (note.getTags().contains(existingTag)) {
                    tagManager.removeTag(note, existingTag);
                    executeMessage = executeMessage.concat(REMOVE_TAG_MESSAGE + existingTag + InterfaceManager.LS);
                } else {
                    // Run the create tag in case existingTag is null, if it is not null, it updates the tag
                    tagManager.createTag(t, false);
                    existingTag = tagManager.getTag(t.getTagName());
                    tagManager.tagNote(note, existingTag);
                    executeMessage = executeMessage.concat(ADD_TAG_MESSAGE + existingTag + InterfaceManager.LS);
                }
            }
            executeMessage = executeMessage + InterfaceManager.LS;
        } catch (IndexOutOfBoundsException exception) {
            executeMessage = COMMAND_UNSUCCESSFUL_MESSAGE;
        }
        return executeMessage.trim();
    }
}
