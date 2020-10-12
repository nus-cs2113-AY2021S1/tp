package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;
import seedu.duke.ui.InterfaceManager;

import java.util.Comparator;
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

    /**
     * Sorts the notes in alphabetical order and returns them if there is a up (A-Z) / down (Z-A) command.
     * If tags exist, maps the tags to the HashMap and gets the corresponding notes
     * For each tag, there will be an ArrayList of the respective notes.
     * The method will then merge the notes in the ArrayLists into 1 large ArrayList.
     * ArrayList is then sorted and returned for the respective up/down commands
     *
     * @return noteString String containing the (filtered) notes (un)sorted
     */
    @Override
    public String execute() {
        StringBuilder noteString = new StringBuilder();
        ArrayList<Note> notes = new ArrayList<>();

        ArrayList<Note> sortedNotes = (ArrayList<Note>) notebook.getNotes().stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                .collect(Collectors.toList());

        if (tags == null) {

            if (isAscendingOrder == null) {
                for (int i = 0; i < notebook.getNotes().size(); i++) {
                    noteString.append(i + 1).append(".").append(notebook.getNotes().get(i).getTitle()).append(InterfaceManager.LS);
                }
            } else {
                noteString = new StringBuilder(getSortedString(noteString.toString(), sortedNotes));
            }

            return noteString.toString();
        }

        Map<Tag, ArrayList<Note>> tag = tagManager.getTagMap();

        // Based on user inputted tags, will store the respective values in an ArrayList
        // E.g. if user input 2 tags, CS2113 and important, will have 2 ArrayList
        //      1 for the values corresponding to CS2113 and the other for important tag
        List<ArrayList<Note>> values = tags.stream()
                .map(tag::get)
                .collect(Collectors.toList());

        for (ArrayList<Note> value : values) {
            for (Note note : value) {
                // Account for duplicates.
                // In case an item has both CS2113 and Important tag
                if (!notes.contains(note)) {
                    notes.add(note);
                }
            }
        }

        // Sort the tagged notes
        ArrayList<Note> sortedTaggedNotes = (ArrayList<Note>) notes.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                .collect(Collectors.toList());


        if (isAscendingOrder == null) {
            for (int i = 0; i < notes.size(); i++) {
                noteString.append(i + 1).append(".").append(notes.get(i).toString());
            }
        } else {
            noteString = new StringBuilder(getSortedString(noteString.toString(), sortedTaggedNotes));

        }

        return noteString.toString();
    }

    /**
     * Method compiles the ArrayList items and appends the items to a String.
     * The ArrayList has already been sorted
     * Method returns either top to bottom or bottom to top to account for ascending/descending sorting
     *
     * @return noteString String containing the notes sorted either ascending ot descending
     */
    private String getSortedString(String noteString, ArrayList<Note> sortedNotes) {
        if (!isAscendingOrder) {
            int j = 1;

            StringBuilder noteStringBuilder = new StringBuilder(noteString);
            for (int i = sortedNotes.size() - 1; i >= 0; i--) {
                noteStringBuilder.append(j).append(".").append(sortedNotes.get(i).getTitle()).append(InterfaceManager.LS);
                j++;
            }
            noteString = noteStringBuilder.toString();

        } else if (isAscendingOrder) {

            StringBuilder noteStringBuilder = new StringBuilder(noteString);
            for (int i = 0; i < sortedNotes.size(); i++) {
                noteStringBuilder.append(i + 1).append(".").append(sortedNotes.get(i).getTitle()).append(InterfaceManager.LS);
            }
            noteString = noteStringBuilder.toString();
        }
        return noteString;
    }
}