package seedu.duke.command;

import seedu.duke.data.notebook.Tag;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Lists all the Notes in the Notebook.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list-n";

    private ArrayList<Tag> tags;
    private boolean isSorted;
    private boolean isAscendingOrder;

    /**
     * Constructs a ListCommand to list all the Notes in the Notebook in a sorted order.
     *
     * @param isAscendingOrder determines the order of the sorting of the Notes.
     */
    public ListCommand(boolean isAscendingOrder) {
        this.isSorted = true;
        this.isAscendingOrder = isAscendingOrder;
        this.tags = null;
    }

    /**
     * Constructs a ListCommand to list all the Notes in the Notebook in the default order.
     */
    public ListCommand() {
        this.isSorted = false;
        this.isAscendingOrder = false;
        this.tags = null;
    }

    /**
     * Constructs a ListCommand to list all the Notes in the Notebook that has the tag(s).
     *
     * @param tags tags of the Notes.
     */
    public ListCommand(ArrayList<Tag> tags) {
        this(false);
        this.tags = tags;
    }

    /**
     * Constructs a ListCommand to list all the Notes in the Notebook, that has the tag(s), in a sorted order.
     *
     * @param isAscendingOrder order of the sort.
     * @param tags tags of the Notes.
     */
    public ListCommand(boolean isAscendingOrder, ArrayList<Tag> tags) {
        this(isAscendingOrder);
        this.tags = tags;
    }

    @Override
    public String execute() {
        return null;
    }
}