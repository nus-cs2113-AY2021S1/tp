package seedu.duke.command;

/**
 * Lists all the Tags.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "list-t";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Lists all the tags.";

    @Override
    public String execute() {
        return tagManager.listTags();
    }
}
