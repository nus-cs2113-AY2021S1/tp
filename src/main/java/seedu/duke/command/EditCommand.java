package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.timetable.Event;

/**
 * Edits a Note in the Notebook or an Event from the Timetable.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD_NOTE = "edit-n";
    public static final String COMMAND_WORD_EVENT = "edit-e";

    private int index;
    private Note note;
    private Event event;
    private boolean isEditNote;

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
