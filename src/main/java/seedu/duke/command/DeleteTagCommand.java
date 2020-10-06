package seedu.duke.command;

/**
 * Deletes an existing Tag.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "delete-t";
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Deleted the tag!";

    private String tagName;

    public DeleteTagCommand(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String execute() {
        tagManager.deleteTag(tagName);
        return COMMAND_SUCCESSFUL_MESSAGE;
    }
}
