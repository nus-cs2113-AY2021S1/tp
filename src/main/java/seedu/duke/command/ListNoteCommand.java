package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.Tag;
import seedu.duke.data.notebook.TagManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;

/**
 * Lists all the Notes in the Notebook.
 */
public class ListNoteCommand extends Command {

    public static final String COMMAND_WORD = "list-n";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Lists all the notes in the Notebook. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG1...] "
            + "[up/down]";

    private ArrayList<String> tags;
    private boolean isSorted;
    private Boolean isAscendingOrder;

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    /**
     * Constructs a ListCommand to list all the Notes in the Notebook in a sorted order.
     *
     * @param isAscendingOrder determines the order of the sorting of the Notes.
     */
    public ListNoteCommand(Boolean isAscendingOrder) {
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
        this.isAscendingOrder = null;
    }

    /**
     * Constructs a ListNoteCommand to list all the Notes in the Notebook that has the tag(s).
     *
     * @param tags tags of the Notes.
     */
    public ListNoteCommand(ArrayList<String> tags) {
        this(false);
        this.tags = tags;
    }

    /**
     * Constructs a ListNoteCommand to list all the Notes in the Notebook, that has the tag(s), in a sorted order.
     *
     * @param isAscendingOrder order of the sort.
     * @param tags tags of the Notes.
     */
    public ListNoteCommand(Boolean isAscendingOrder, ArrayList<String> tags) {
        this(isAscendingOrder);
        this.tags = tags;
    }

    @Override
    public String execute() {
        String noteString = "";
        ArrayList<Note> notes = new ArrayList<>();

        ArrayList<Note> sortedNotes = (ArrayList<Note>) notebook.getNotes().stream()
                .filter((s) -> s instanceof Note)
                .sorted((a, b) -> a.getTitle().toLowerCase().compareTo(b.getTitle().toLowerCase()))
                .collect(Collectors.toList());

        if (tags == null) {

            if (isAscendingOrder == null) {
                for (int i = 0; i < notebook.getNotes().size(); i++) {
                    noteString += (i + 1) + "." + notebook.getNotes().get(i).toString();
                }
            } else if (!isAscendingOrder) {
                int j = 1;

                for (int i = sortedNotes.size(); i > 0; i--) {
                    noteString += (j) + "." + sortedNotes.get(i).toString();
                    j++;
                }

            } else if (isAscendingOrder) {

                for (int i = 0; i < sortedNotes.size(); i++) {
                    noteString += (i + 1) + "." + sortedNotes.get(i).toString();
                }
            }
        } else {
            Map<Tag, ArrayList<Note>> tag = TagManager.getTagMap();

            // Based on user inputted tags, will store the respective values in an ArrayList
            // E.g. if user input 2 tags, CS2113 and important, will have 2 ArrayList
            //      1 for the values corresponding to CS2113 and the other for important tag
            List<ArrayList<Note>> values = tags.stream()
                            .map(tag::get)
                            .collect(Collectors.toList());

            for (int i = 0; i < values.size(); i++) {
                for (int j = 0; j < values.get(i).size(); j++) {
                    // Account for duplicates.
                    // In case an item has both CS2113 and Important tag
                    if (!notes.contains(values.get(i).get(j))) {
                        notes.add(values.get(i).get(j));
                    }
                }
            }

            // Sort the tagged notes
            ArrayList<Note> sortedTaggedNotes = (ArrayList<Note>) notes.stream()
                    .filter((s) -> s instanceof Note)
                    .sorted((a, b) -> a.getTitle().toLowerCase().compareTo(b.getTitle().toLowerCase()))
                    .collect(Collectors.toList());


            if (isAscendingOrder == null) {
                for (int i = 0; i < notes.size(); i++) {
                    noteString += (i + 1) + "." + notes.get(i).toString();
                }
            } else if (!isAscendingOrder) {
                int j = 1;

                for (int i = sortedTaggedNotes.size(); i > 0; i--) {
                    noteString += (j) + "." + sortedTaggedNotes.get(i).toString();
                    j++;
                }

            } else if (isAscendingOrder) {

                for (int i = 0; i < sortedTaggedNotes.size(); i++) {
                    noteString += (i + 1) + "." + sortedTaggedNotes.get(i).toString();
                }
            }
        }

        return noteString;
    }
}