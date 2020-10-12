package seedu.duke.command;

import seedu.duke.data.notebook.Tag;
import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;

/**
 * Deletes an existing Tag.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "delete-t";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Deletes a tag. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG1...]";

    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Deleted the tag! ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "The tag does not exist! ";

    private ArrayList<Tag> tags;

    /**
     * Constructs a DeleteNoteCommand to delete tag(s).
     */
    public DeleteTagCommand(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String execute() {
        boolean executeSuccessful;
        String executeMessage = "";

        // Deletes all the tags in the given list
        for (Tag t : tags) {
            Tag existingTag = tagManager.getTag(t.getTagName());
            executeSuccessful = tagManager.deleteTag(t);
            if (executeSuccessful) {
                executeMessage = executeMessage.concat(COMMAND_SUCCESSFUL_MESSAGE + existingTag);
            } else {
                executeMessage = executeMessage.concat(COMMAND_UNSUCCESSFUL_MESSAGE + t);
            }
            executeMessage = executeMessage + InterfaceManager.LS;
        }
        return executeMessage.trim();
    }
}
