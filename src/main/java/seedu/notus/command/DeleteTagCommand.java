package seedu.notus.command;

import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.notus.util.CommandMessage.DELETE_TAG_SUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.DELETE_TAG_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.FILE_WRITE_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.HEADER_DELETE_TAG;

//@@author Chongjx
/**
 * Deletes an existing Tag.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "delete-t";

    private ArrayList<Tag> tags;

    /**
     * Constructs a DeleteNoteCommand to delete tag(s).
     */
    public DeleteTagCommand(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String execute() {
        ArrayList<String> executedMessage = tagManager.deleteTag(tags, DELETE_TAG_SUCCESSFUL_MESSAGE,
                DELETE_TAG_UNSUCCESSFUL_MESSAGE);
        executedMessage.add(0, HEADER_DELETE_TAG);

        try {
            storageManager.saveAllNoteDetails(false);
        } catch (IOException exception) {
            Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
        }

        return Formatter.formatString(executedMessage, true);
    }
}
