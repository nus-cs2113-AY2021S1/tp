package seedu.duke.command;

import com.diogonunes.jcolor.Attribute;
import seedu.duke.data.notebook.Tag;
import seedu.duke.util.Formatter;

import java.util.ArrayList;

import static com.diogonunes.jcolor.Ansi.colorize;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;

/**
 * Creates Tag for the notes.
 */
public class CreateTagCommand extends Command {

    public static final String COMMAND_WORD = "create-t";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Creates a tag. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG NAME [TAG COLOR]"
            + Formatter.LS + "(Available colors: "
            + colorize(Tag.COLOR_WHITE_STRING + ", ", Attribute.BRIGHT_WHITE_TEXT())
            + colorize(Tag.COLOR_RED_STRING + ", ", Attribute.BRIGHT_RED_TEXT())
            + colorize(Tag.COLOR_GREEN_STRING + ", ", Attribute.BRIGHT_GREEN_TEXT())
            + colorize(Tag.COLOR_BLUE_STRING + ", ", Attribute.BRIGHT_BLUE_TEXT())
            + colorize(Tag.COLOR_YELLOW_STRING + ", ", Attribute.BRIGHT_YELLOW_TEXT())
            + colorize(Tag.COLOR_MAGENTA_STRING + ", ", Attribute.BRIGHT_MAGENTA_TEXT())
            + colorize(Tag.COLOR_CYAN_STRING, Attribute.BRIGHT_CYAN_TEXT())
            + colorize(")", Attribute.BRIGHT_WHITE_TEXT());

    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Created a tag! ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "Tag already exists! ";

    private ArrayList<Tag> tags;

    /**
     * Constructs a CreateTagCommand to create tag(s).
     */
    public CreateTagCommand(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String execute() {
        ArrayList<String> executedMessage = tagManager.createTag(tags, COMMAND_SUCCESSFUL_MESSAGE,
                COMMAND_UNSUCCESSFUL_MESSAGE);
        return Formatter.formatString(executedMessage, false, true);
    }
}
