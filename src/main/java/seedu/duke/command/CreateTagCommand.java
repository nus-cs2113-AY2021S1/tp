package seedu.duke.command;

import seedu.duke.data.notebook.Tag;
import seedu.duke.data.notebook.Tag.TagColor;
import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;

/**
 * Creates Tag for the notes.
 */
public class CreateTagCommand extends Command {

    public static final String COLOR_RED_STRING = TagColor.COLOR_RED.getColor();
    public static final String COLOR_GREEN_STRING = TagColor.COLOR_GREEN.getColor();
    public static final String COLOR_BLUE_STRING = TagColor.COLOR_BLUE.getColor();
    public static final String COLOR_YELLOW_STRING = TagColor.COLOR_YELLOW.getColor();
    public static final String COLOR_PURPLE_STRING = TagColor.COLOR_PURPLE.getColor();
    public static final String COLOR_CYAN_STRING = TagColor.COLOR_CYAN.getColor();
    public static final String COLOR_WHITE_STRING = TagColor.COLOR_WHITE.getColor();
    public static final String COLOR_RESET_STRING = TagColor.COLOR_RESET.getColor();

    public static final String COMMAND_WORD = "create-t";
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Created a tag! ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "Tag already exists! ";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Creates a tag. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG NAME [TAG COLOR]"
            + COLOR_RESET_STRING + InterfaceManager.LS + "(Available colors: "
            + COLOR_WHITE_STRING + Tag.COLOR_WHITE_STRING + ", "
            + COLOR_RED_STRING + Tag.COLOR_RED_STRING + ", "
            + COLOR_GREEN_STRING + Tag.COLOR_GREEN_STRING + ", "
            + COLOR_BLUE_STRING + Tag.COLOR_BLUE_STRING + ", "
            + COLOR_YELLOW_STRING + Tag.COLOR_YELLOW_STRING + ", "
            + COLOR_CYAN_STRING + Tag.COLOR_CYAN_STRING + ", "
            + COLOR_PURPLE_STRING + Tag.COLOR_PURPLE_STRING
            + COLOR_RESET_STRING + ")";

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    private ArrayList<Tag> tags;

    /**
     * Constructs a CreateTagCommand to create tag(s).
     */
    public CreateTagCommand(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String execute() {
        boolean executeSuccessful;
        String executeMessage = "";

        // Creates all the tags in the given list
        for (Tag t : tags) {
            executeSuccessful = tagManager.createTag(t, true);
            if (executeSuccessful) {
                executeMessage = executeMessage.concat(COMMAND_SUCCESSFUL_MESSAGE);
            } else {
                executeMessage = executeMessage.concat(COMMAND_UNSUCCESSFUL_MESSAGE);
            }
            executeMessage = executeMessage.concat(tagManager.getTag(t.getTagName()) + InterfaceManager.LS);
        }
        return executeMessage.trim();
    }
}
