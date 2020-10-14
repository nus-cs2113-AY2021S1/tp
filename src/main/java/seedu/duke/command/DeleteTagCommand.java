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
        return tagManager.deleteTag(tags, COMMAND_SUCCESSFUL_MESSAGE, COMMAND_UNSUCCESSFUL_MESSAGE);
    }
}
