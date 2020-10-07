package seedu.duke.command;

import seedu.duke.data.notebook.Tag;

import java.util.ArrayList;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;

/**
 * Lists all the Notes in the Notebook.
 */
public class ListNoteCommand extends Command {

    public static final String COMMAND_WORD = "list-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Lists all the notes in the Notebook. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG1 TAG2...] "
            + "[up/down]";

    private ArrayList<Tag> tags;
    private boolean isSorted;
    private boolean isAscendingOrder;

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    /**
     * Constructs a ListCommand to list all the Notes in the Notebook in a sorted order.
     *
     * @param isAscendingOrder determines the order of the sorting of the Notes.
     */
    public ListNoteCommand(boolean isAscendingOrder) {
        this.tags = null;
        this.isSorted = true;
        this.isAscendingOrder = isAscendingOrder;
    }

    /**
     * Constructs a ListNoteCommand to list all the Notes in the Notebook in the default order.
     */
    public ListNoteCommand() {
        this.tags = null;
        this.isSorted = false;
        this.isAscendingOrder = false;
    }

    /**
     * Constructs a ListNoteCommand to list all the Notes in the Notebook that has the tag(s).
     *
     * @param tags tags of the Notes.
     */
    public ListNoteCommand(ArrayList<Tag> tags) {
        this(false);
        this.tags = tags;
    }

    /**
     * Constructs a ListNoteCommand to list all the Notes in the Notebook, that has the tag(s), in a sorted order.
     *
     * @param isAscendingOrder order of the sort.
     * @param tags tags of the Notes.
     */
    public ListNoteCommand(boolean isAscendingOrder, ArrayList<Tag> tags) {
        this(isAscendingOrder);
        this.tags = tags;
    }

    @Override
    public String execute() {
        return null;
    }
}