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
    private boolean isEditNote;
    private Note note;
    private Event event;

    /**
     * Constructs an EditCommand to edit a Note.
     *
     * @param index of the Note to be edited.
     * @param note to be edited.
     */
    public EditCommand(int index, Note note) {
        this.index = index;
        this.note = note;
        this.isEditNote = true;
        this.event = null;
    }

    /**
     * Constructs an EditCommand to edit a Note.
     *
     * @param index of the Event to be edited.
     * @param event to be edited.
     */
    public EditCommand(int index, Event event) {
        this.index = index;
        this.event = event;
        this.isEditNote = false;
        this.note = null;
    }

    @Override
    public String execute() {
        return null;
    }
}
