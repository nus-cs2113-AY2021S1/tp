package seedu.duke.command;

/**
 * Pins or unpins a Note in the Notebook.
 */
public class PinCommand extends Command {

    public static final String COMMAND_WORD = "pin-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Pins or unpins a note. Parameters: ";

    private int index;
    private String title;
    private boolean isPinByIndex;

    /**
     * Constructs a PinCommand to pin or unpin a Note in the Notebook by the index.
     *
     * @param index of the Note.
     */
    public PinCommand(int index) {
        this.index = index;
        this.title = null;
        this.isPinByIndex = true;
    }

    /**
     * Constructs a PinCommand to pin or unpin a Note in the Notebook by the title.
     *
     * @param title of the Note.
     */
    public PinCommand(String title) {
        this.index = NULL_INT;
        this.title = title;
        this.isPinByIndex = false;
    }

    @Override
    public String execute() {
        return null;
    }
}
