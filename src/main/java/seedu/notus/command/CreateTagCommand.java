package seedu.notus.command;

import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import java.util.ArrayList;

import static seedu.notus.util.CommandMessage.CREATE_TAG_SUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.CREATE_TAG_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.HEADER_CREATE_TAG;

//@@author Chongjx
/**
 * Creates Tag for the notes.
 */
public class CreateTagCommand extends Command {

    public static final String COMMAND_WORD = "create-t";

    private ArrayList<Tag> tags;

    /**
     * Constructs a CreateTagCommand to create tag(s).
     */
    public CreateTagCommand(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String execute() {
        ArrayList<String> executedMessage = tagManager.createTag(tags, CREATE_TAG_SUCCESSFUL_MESSAGE,
                CREATE_TAG_UNSUCCESSFUL_MESSAGE);
        executedMessage.add(0, HEADER_CREATE_TAG);
        return Formatter.formatString(executedMessage, true);
    }
}
