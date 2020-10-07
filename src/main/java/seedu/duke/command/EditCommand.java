package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.timetable.Event;

import static seedu.duke.util.PrefixSyntax.PREFIX_CONTENT;
import static seedu.duke.util.PrefixSyntax.PREFIX_DATETIME;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_LINE;
import static seedu.duke.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.duke.util.PrefixSyntax.PREFIX_REMIND;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;

/**
 * Edits a Note in the Notebook or an Event from the Timetable.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD_NOTE = "edit-n";
    public static final String COMMAND_WORD_EVENT = "edit-e";

    public static final String COMMAND_USAGE_NOTE = COMMAND_WORD_NOTE + ": Edits a note in the notebook. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            +  PREFIX_LINE + " LINE "
            +  PREFIX_CONTENT + " CONTENT "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG1 TAG2...] ";

    public static final String COMMAND_USAGE_EVENT = COMMAND_WORD_EVENT + ": Edits an event in the timetable. "
            + "Parameters: " + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + PREFIX_DATETIME + " DATE_TIME "
            + "[" + PREFIX_RECURRING + " RECURRING] "
            + "[" + PREFIX_REMIND + " REMIND]";

    private int index;
    private Note note;
    private Event event;
    private boolean isEditNote;

    public static String getCommandUsageNote() {
        return COMMAND_USAGE_NOTE;
    }

    public static String getCommandUsageEvent() {
        return COMMAND_USAGE_EVENT;
    }

    /**
     * Constructs an EditCommand to edit a Note.
     *
     * @param index of the Note to be edited.
     * @param note to be edited.
     */
    public EditCommand(int index, Note note) {
        this.index = index;
        this.note = note;
        this.event = null;
        this.isEditNote = true;
    }

    /**
     * Constructs an EditCommand to edit an Event.
     *
     * @param index of the Event to be edited.
     * @param event to be edited.
     */
    public EditCommand(int index, Event event) {
        this.index = index;
        this.note = null;
        this.event = event;
        this.isEditNote = false;
    }

    @Override
    public String execute() {
        return null;
    }
}
