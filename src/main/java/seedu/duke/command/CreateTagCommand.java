package seedu.duke.command;

/**
 * Creates Tag for the notes.
 */
public class CreateTagCommand extends Command {

    public static final String COMMAND_WORD = "create-t";
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Created a tag!";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "Tag already exists!";

    public static final String MESSAGE_USAGE = "";

    private String tagName;
    private String tagColor;

    /**
     * Constructs a TagCommand to list all the Tags.
     */
    public CreateTagCommand(String tagName, String tagColor) {
        this.tagName = tagName;
        this.tagColor = tagColor;
    }

    @Override
    public String execute() {
        boolean executeSuccessful = tagManager.createTag(tagName, tagColor);
        String executeMessage;

        if (executeSuccessful) {
            executeMessage = COMMAND_SUCCESSFUL_MESSAGE;
        } else {
            executeMessage = COMMAND_UNSUCCESSFUL_MESSAGE;
        }
        executeMessage = executeMessage.concat(tagManager.getTag(tagName).toString());

        return executeMessage;
    }
}
