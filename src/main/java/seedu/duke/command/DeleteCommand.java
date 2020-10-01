package seedu.duke.command;

/**
 * Deletes a Note from the Notebook or an Event from the Timetable.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD_NOTE = "delete-n";
    public static final String COMMAND_WORD_EVENT = "delete-e";

    private int index;
    private boolean isDeleteNote;

    /**
     * Constructs a DeleteCommand to delete a Note or an Event.
     *
     * @param index of the item to be deleted.
     * @param isDeleteNote determines to delete a Note or an Event.
     */
    public DeleteCommand(int index, boolean isDeleteNote) {
        this.index = index;
        this.isDeleteNote = isDeleteNote;
    }

    @Override
    public String execute() {
        return null;
    }
}
