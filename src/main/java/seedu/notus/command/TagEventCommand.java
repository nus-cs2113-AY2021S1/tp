package seedu.notus.command;

import seedu.notus.data.timetable.Event;
import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;

//@@author Chongjx
/**
 * Tags or untags a Note.
 */
public class TagEventCommand extends Command {

    public static final String COMMAND_WORD = "tag-e";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Tags or untags an event. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...]";

    public static final String TAG_EVENT_MESSAGE = "Added the tag to the event! ";
    public static final String UNTAG_EVENT_MESSAGE = "Removed the tag from the event! ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "Invalid index input!";
    public static final String TAG_EVENT_HEADER = "Tagging event...";

    private int index;
    private ArrayList<Tag> tags;

    /**
     * Constructs a TagCommand to tag or untag a Note.
     */
    public TagEventCommand(int index, ArrayList<Tag> tags) {
        this.index = index;
        this.tags = tags;
    }

    @Override
    public String execute() {
        try {
            assert index >= 0;
            Event event = timetable.getEvent(index);
            ArrayList<String> executedMessage = tagManager.tagAndUntag(event, tags, TAG_EVENT_MESSAGE,
                    UNTAG_EVENT_MESSAGE);
            executedMessage.add(0, TAG_EVENT_HEADER);
            return Formatter.formatString(executedMessage, true);
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        }
    }
}
