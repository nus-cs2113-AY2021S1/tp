package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Tag;
import seedu.duke.ui.Formatter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static seedu.duke.ui.Formatter.formatNotes;
import static seedu.duke.ui.Formatter.getSortedPinnedNotes;
import static seedu.duke.ui.Formatter.getUnsortedPinnedNotes;
import static seedu.duke.ui.Formatter.getSortedString;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;

/**
 * Lists all the Notes in the Notebook.
 */
public class ListNoteCommand extends Command {

    public static final String COMMAND_WORD = "list-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Lists all the notes in the Notebook. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG1...] "
            + "[/sort up OR down]";

    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Here are the list of notes: " + Formatter.LS;
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE_INVALID_TAG = "Your tags return no result."
            + " Please try an alternative tag or check your spellings";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE_EMPTY_NOTEBOOK = "The notebook is empty!";

    private ArrayList<String> tags;
    private boolean isSorted;
    private boolean isArchived;
    private Boolean isAscendingOrder;

    /**
     * Constructs a ListCommand to list all the Notes in the Notebook in a sorted order.
     *
     * @param isAscendingOrder determines the order of the sorting of the Notes.
     */
    public ListNoteCommand(Boolean isAscendingOrder) {
        this.tags = null;
        this.isSorted = true;
        this.isArchived = false;
        this.isAscendingOrder = isAscendingOrder;
    }

    /**
     * Constructs a ListNoteCommand to list all the Notes in the Notebook in the default order.
     */
    public ListNoteCommand() {
        this.tags = null;
        this.isSorted = false;
        this.isArchived = false;
        this.isAscendingOrder = null;
    }

    /**
     * Constructs a ListNoteCommand to list all the Notes in the Archived Notebook.
     */
    public ListNoteCommand(boolean isArchived) {
        this.tags = null;
        this.isSorted = false;
        this.isArchived = true;
        this.isAscendingOrder = null;
    }

    /**
     * Constructs a ListNoteCommand to list all the Notes in the Notebook that has the tag(s).
     *
     * @param tags tags of the Notes.
     */
    public ListNoteCommand(ArrayList<String> tags) {
        this.isSorted = false;
        this.isArchived = false;
        this.isAscendingOrder = null;
        this.tags = tags;
    }

    /**
     * Constructs a ListNoteCommand to list all the Notes in the Notebook, that has the tag(s), in a sorted order.
     *
     * @param isAscendingOrder order of the sort.
     * @param tags tags of the Notes.
     */
    public ListNoteCommand(Boolean isAscendingOrder, ArrayList<String> tags) {
        this.isAscendingOrder = isAscendingOrder;
        this.isSorted = true;
        this.isArchived = false;
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
        StringBuilder formattedString;
        StringBuilder pinnedNotesSorted;
        StringBuilder unpinnedNotesSorted;
        ArrayList<Note> pinnedNotes = new ArrayList<>();
        ArrayList<Note> unpinnedNotes = new ArrayList<>();
        ArrayList<Note> notes = new ArrayList<>();
        ArrayList<Note> archivedNotes;


        if (isArchived) {
            archivedNotes = notebook.getArchivedNotes();
            return formatNotes(archivedNotes).toString();
        }

        for (int i = 0; i < notebook.getNotes().size(); i++) {
            String pinnedNoteStatus = notebook.getNote(i).getPinned();
            if (pinnedNoteStatus.equals("Y")) {
                pinnedNotes.add(notebook.getNote(i));
            } else if (pinnedNoteStatus.equals("N")) {
                unpinnedNotes.add(notebook.getNote(i));
            }
        }

        // Takes the notes in the notebook and sorts them according to title, alphabetically (a-z)
        ArrayList<Note> sortedNotes = (ArrayList<Note>) notebook.getNotes().stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                .collect(Collectors.toList());

        if (tags == null) {
            if (isAscendingOrder == null && pinnedNotes.isEmpty()) {
                formattedString = formatNotes(notebook.getNotes());
            } else if (isAscendingOrder == null) {
                formattedString = getSortedPinnedNotes(pinnedNotes, unpinnedNotes);
            } else if (pinnedNotes.isEmpty()) {
                formattedString = getSortedString(sortedNotes, isAscendingOrder);
            } else {
                pinnedNotesSorted = getSortedString(pinnedNotes, isAscendingOrder);
                unpinnedNotesSorted = getSortedString(unpinnedNotes, isAscendingOrder);
                formattedString = getUnsortedPinnedNotes(pinnedNotesSorted, unpinnedNotesSorted);
            }

            if (formattedString.toString().isBlank()) {
                return Formatter.LS + COMMAND_UNSUCCESSFUL_MESSAGE_EMPTY_NOTEBOOK;
            }
            return Formatter.LS + COMMAND_SUCCESSFUL_MESSAGE + formattedString.toString();
        }

        // Obtaining ArrayList<String> of tags and parsing it to get an ArrayList<Tag> of tags
        Map<Tag, ArrayList<Note>> tagMap = tagManager.getTagMap();
        ArrayList<Tag> tagList = new ArrayList<>();

        for (String tag : tags) {
            Tag currentTag = tagManager.getTag(tag);

            if (currentTag != null) {
                tagList.add(currentTag);
            }
        }

        // If the user inputted tags does not match any of the existing tags.
        if (tagList.isEmpty()) {
            return Formatter.LS + COMMAND_UNSUCCESSFUL_MESSAGE_INVALID_TAG;
        }

        // Based on user inputted tags, will store the respective values in an ArrayList
        // E.g. if user input 2 tags, CS2113 and important, will have 2 ArrayList
        //      1 for the values corresponding to CS2113 and the other for important tag
        List<ArrayList<Note>> values = tagList.stream()
                .map(tagMap::get)
                .collect(Collectors.toList());

        for (ArrayList<Note> value : values) {
            for (Note note : value) {
                // Account for note duplicates (multiple tags).
                // For e.g. In case an item has both CS2113 and Important tag
                if (!notes.contains(note)) {
                    notes.add(note);
                }
            }
        }

        // Checking for empty notes List
        if (notes.isEmpty()) {
            return Formatter.LS + COMMAND_UNSUCCESSFUL_MESSAGE_INVALID_TAG;
        }

        // Sort the tagged notes
        ArrayList<Note> sortedTaggedNotes = (ArrayList<Note>) notes.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                .collect(Collectors.toList());

        if (isAscendingOrder == null) {
            formattedString = formatNotes(notes);
        } else {
            formattedString = getSortedString(sortedTaggedNotes, isAscendingOrder);
        }
        return Formatter.LS + COMMAND_SUCCESSFUL_MESSAGE + formattedString.toString();
    }
}