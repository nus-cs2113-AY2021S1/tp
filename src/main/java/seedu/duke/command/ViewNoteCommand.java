package seedu.duke.command;

/**
 * Views a specific Note in the Notebook.
 */
public class ViewNoteCommand extends Command {

    public static final String COMMAND_WORD = "view-n";

    private int index;
    private String title;
    private boolean isViewByIndex;

    /**
     * Constructs a ViewNoteCommand to view a Note by the index.
     *
     * @param index of the Note.
     */
    public ViewNoteCommand(int index) {
        this.index = index;
        this.title = null;
        this.isViewByIndex = true;
    }

    /**
     * Constructs a ViewNoteCommand to view a Note by the title.
     *
     * @param title of the Note.
     */
    public ViewNoteCommand(String title) {
        this.index = NULL_INT;
        this.title = title;
        this.isViewByIndex = false;
    }

    @Override
    public String execute() {
        return null;
    }
}
