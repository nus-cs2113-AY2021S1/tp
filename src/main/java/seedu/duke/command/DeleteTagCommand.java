package seedu.duke.command;

/**
 * Deletes an existing Tag.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "delete-t";
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Deleted the tag!";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "The tag does not exist!";

    public static final String MESSAGE_USAGE = "";

    private String tagName;

    public DeleteTagCommand(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String execute() {
        boolean executeSuccessful = tagManager.deleteTag(tagName);
        if (executeSuccessful) {
            return COMMAND_SUCCESSFUL_MESSAGE;
        } else {
            return COMMAND_UNSUCCESSFUL_MESSAGE;
        }
    }
}
