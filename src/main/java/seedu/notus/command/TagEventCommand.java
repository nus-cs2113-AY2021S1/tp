package seedu.notus.command;

import seedu.notus.data.timetable.Event;
import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.notus.util.CommandMessage.*;

//@@author Chongjx
/**
 * Tags or untags an Event.
 */
public class TagEventCommand extends Command {

    public static final String COMMAND_WORD = "tag-e";

    private int index;
    private ArrayList<Tag> tags;

    /**
     * Constructs a TagCommand to tag or untag an Event.
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
            ArrayList<String> executedMessage = tagManager.tagAndUntag(event, tags, TAG_MESSAGE,
                    UNTAG_MESSAGE);
            executedMessage.add(0, TAG_HEADER);

            // save the changed details
            try {
                storageManager.saveTimetable();
            } catch (IOException e) {
                return Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
            }

            return Formatter.formatString(executedMessage, true);
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(INDEX_OUT_OF_RANGE_MESSAGE);
        }
    }
}
