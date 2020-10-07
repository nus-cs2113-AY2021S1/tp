package seedu.duke.command;

/**
 * Lists all the Tags.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "list-t";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Lists all the tags.";

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    @Override
    public String execute() {
        return tagManager.listTags();
    }
}
