package seedu.duke.command;

/**
 * Lists, creates or delete Tags.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    private String tag;
    private boolean isListTag;

    /**
     * Constructs a TagCommand to list all the Tags.
     */
    public TagCommand() {
        isListTag = true;
    }

    /**
     * Constructs a TagCommand to create or delete a Tag.
     *
     * @param tag to match with the list of Tags.
     */
    public TagCommand(String tag) {
        this.tag = tag;
        isListTag = false;
    }

    @Override
    public String execute() {
        return null;
    }
}
