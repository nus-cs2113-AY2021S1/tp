package seedu.notus.command;

import seedu.notus.data.tag.TaggableObject;
import seedu.notus.data.notebook.Note;
import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import java.util.ArrayList;
import java.util.Map;

import static seedu.notus.ui.Formatter.formatNotes;
import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;

//@@author R-Ramana
/**
 * Lists all the Notes in the Notebook.
 */
public class ListNoteCommand extends Command {

    public static final String COMMAND_WORD = "list-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Lists all the notes in the Notebook. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG1...] "
            + "[/sort up OR down]";

    public static final String PINNED_NOTES_MESSAGE = "Here are the list of pinned notes:";
    public static final String UNPINNED_NOTES_MESSAGE = "Here are the list of unpinned notes:";
    public static final String ARCHIVE_NOTES_MESSAGE = "Here are the list of archived notes:";
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Here are the list of notes:";
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
     * Depending on the constructor that is used, there are multiple ways to list out the notes in a notebook.
     * Method calls other methods for sorting alphabetically, splitting pinned/unpinned notes, listing archived notes.
     * Also gets the tags and the mapped notes if needed.
     *
     * @return String value to be formatted and printed out to the CLI.
     */
    @Override
    public String execute() {

        ArrayList<Note> notes = new ArrayList<>();
        ArrayList<Note> pinned;
        ArrayList<Note> unpinned;

        // if user inputs /archive, regardless of other commands will only display notes that have been archived
        // no sorting, no viewing, no filtering of notes in the archived notebook.
        if (isArchived) {
            notes = notebook.getArchivedNotes();
            return formatNotes(ARCHIVE_NOTES_MESSAGE, notes, notebook);
        }

        // if no /archive or /tags and there is no pinned notes at all this if-else block will be executed
        if (!notebook.checkPinned() && tags == null) {
            if (!isSorted) {
                // get notes in default order
                notes = notebook.getNotes();
            } else if (isSorted) {
                // get notes sorted alphabetically all from the main notebook
                notes = notebook.getSortedList(isAscendingOrder, (Boolean) null);
            }

            if (notes.isEmpty()) {
                return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE_EMPTY_NOTEBOOK);
            }

            return formatNotes(COMMAND_SUCCESSFUL_MESSAGE, notes, notebook);
        }

        // if no /archive or /tags and there are pinned notes this if-else block will be executed
        if (notebook.checkPinned() && tags == null) {
            if (!isSorted) {
                // get notes in default order
                pinned = notebook.getPinnedNotes();
                unpinned = notebook.getUnpinnedNotes();
            } else {
                // get notes sorted alphabetically from the main notebook (isPinned is a flag to get filtered notes)
                pinned = notebook.getSortedList(isAscendingOrder, true);
                unpinned = notebook.getSortedList(isAscendingOrder, false);
            }

            return formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE, pinned, unpinned, notebook);
        }

        // Obtaining ArrayList<String> of tags and parsing it to get an ArrayList<Tag> of tags
        Map<Tag, ArrayList<TaggableObject>> tagMap = tagManager.getTagMap();
        ArrayList<Tag> tagList = new ArrayList<>();

        for (String tag : tags) {
            Tag currentTag = tagManager.getTag(tag);

            if (currentTag != null) {
                tagList.add(currentTag);
            }
        }

        // Check if the user inputted tags match any of the existing tags.
        if (tagList.isEmpty()) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE_INVALID_TAG);
        }

        // Based on user inputted tags, will store the respective values (notes) in an ArrayList
        // E.g. if user input 2 tags, CS2113 and important, will have 2 ArrayList of notes
        //      1 for the values corresponding to CS2113 and the other for important tag
        ArrayList<ArrayList<Note>> values = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            ArrayList<TaggableObject> taggableObject = tagMap.get(tagList.get(i));
            ArrayList<Note> tagObjectsAsNote = new ArrayList<>();
            for (TaggableObject tagObject : taggableObject) {
                if (tagObject instanceof Note) {
                    tagObjectsAsNote.add((Note) tagObject);
                }
            }
            values.add(tagObjectsAsNote);
        }

        // Account for note duplicates (multiple tags).
        // For e.g. In case an item has both CS2113 and Important tag
        for (ArrayList<Note> value : values) {
            for (Note note : value) {
                if (!notes.contains(note)) {
                    notes.add(note);
                }
            }
        }

        // Checking for empty notes List
        if (notes.isEmpty()) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE_EMPTY_NOTEBOOK);
        }

        // if no /archive or pinned notes and there are /tags
        if (!notebook.checkPinned() && tags != null) {
            ArrayList<Note> sortedTaggedNotes = new ArrayList<>();

            if (isSorted) {
                // Sort the tagged notes
                sortedTaggedNotes = notebook.getSortedList(isAscendingOrder, null, notes);
            } else {
                sortedTaggedNotes = notes;
            }
            return formatNotes(COMMAND_SUCCESSFUL_MESSAGE, sortedTaggedNotes, notebook);
        }

        // if no /archive and there are both pinned notes and /tags
        if (notebook.checkPinned() && tags != null) {
            if (!isSorted) {
                // get notes in default order
                pinned = notebook.getPinnedNotes(notes);
                unpinned = notebook.getUnpinnedNotes(notes);
            } else {
                // get notes sorted alphabetically from the main notebook (isPinned is a flag to get filtered notes)
                pinned = notebook.getSortedList(isAscendingOrder, true, notes);
                unpinned = notebook.getSortedList(isAscendingOrder, false, notes);
            }

            return formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE, pinned, unpinned, notebook);
        }

        return formatNotes(COMMAND_SUCCESSFUL_MESSAGE, notes, notebook);
    }
}