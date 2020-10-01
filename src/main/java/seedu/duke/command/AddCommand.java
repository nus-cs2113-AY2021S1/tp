package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.timetable.Event;

/**
 * Adds a Note into the Notebook or an Event into the Timetable.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD_NOTE = "add-n";
    public static final String COMMAND_WORD_EVENT = "add-e";

    private Note note;
    private Event event;
    private boolean isAddNote;

    /**
     * Constructs an AddCommand to add a Note into the Notebook.
     *
     * @param note to be added.
     */
    public AddCommand(Note note) {
        this.note = note;
        this.event = null;
        this.isAddNote = true;
    }

    /**
     * Constructs an AddCommand to add an Event into the Timetable.
     *
     * @param event to be added.
     */
    public AddCommand(Event event) {
        this.event  = event;
        this.note = null;
        this.isAddNote = false;
    }

    @Override
    public String execute() {
        return null;
    }
}
