package seedu.duke.command;

/**
 * Finds Notes in the Notebook.(Possible to add find in event too)
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Finds a note. Parameters: ";

    private String keywords;

    /**
     * Constructs a FindCommand to find Notes in the Notebook given the keyword.
     *
     * @param keywords to look for in the Notebook.
     */
    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute() {
        return null;
    }
}
