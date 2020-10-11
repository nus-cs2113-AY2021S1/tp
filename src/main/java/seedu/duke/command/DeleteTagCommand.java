package seedu.duke.command;

import seedu.duke.data.notebook.Tag;
import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;

/**
 * Deletes an existing Tag.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "delete-t";
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Deleted the tag! ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "The tag does not exist! ";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Deletes a tag. Parameters: TAG_NAME";

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

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
