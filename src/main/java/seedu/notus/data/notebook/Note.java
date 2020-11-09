package seedu.notus.data.notebook;

import seedu.notus.data.tag.Tag;
import seedu.notus.data.tag.TaggableObject;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_ARCHIVE;
import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;
import static seedu.notus.util.PrefixSyntax.PREFIX_PIN;
import static seedu.notus.ui.Formatter.LS;

//@@author Nazryl
/**
 * Represents a Note. Contains all the information of a note.
 */
public class Note extends TaggableObject {

    private String title;
    private ArrayList<String> content;
    private Boolean isPinned;
    private boolean isArchived;

    /**
     * Constructs a Note object with its title, content and pinned status provided.
     *
     * @param title of the note.
     * @param content of the note.
     * @param isPinned status of the note.
     */
    public Note(String title, ArrayList<String> content, Boolean isPinned, boolean isArchived) {
        super();
        this.title = title;
        this.content = content;
        this.isPinned = isPinned;
        this.isArchived = isArchived;
    }

    /**
     * Constructs a Note object with its title, content, pinned status and tags provided.
     *
     * @param title of the note.
     * @param content of the note.
     * @param isPinned status of the note.
     * @param tags of the note.
     */
    public Note(String title, ArrayList<String> content, Boolean isPinned, boolean isArchived, ArrayList<Tag> tags) {
        this(title, content, isPinned, isArchived);
        super.setTags(tags);
    }

    /**
     * Gets the title of note from existing data.
     *
     * @return title of the note.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of note from the changes.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of note from existing data.
     *
     * @return content of the note.
     */
    public ArrayList<String> getContent() {
        return content;
    }

    public String getContentString() {
        String contentString = "";

        for (String information: content) {
            contentString += information + LS;
        }
        return contentString;
    }

    /**
     * Sets the content of note from the changes.
     */
    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    /**
     * Gets the pinned status of a note.
     *
     * @return true if note is pinned, false otherwise.
     */
    public boolean getPinned() {
        return (isPinned);
    }

    public String getPinnedString() {
        return (isPinned ? "Pinned" : "Unpinned");
    }

    public void togglePinned() {
        isPinned = !isPinned;
    }

    public void toggleArchived() {
        isArchived = !isArchived;
    }

    public boolean getIsArchived() {
        return this.isArchived;
    }

    public String getIsArchivedString() {
        return (isArchived ? "Archived" : "Unarchived");
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public String toSaveString() {
        String noteDetails = "";
        String tagDetails = "";

        for (Tag tag: this.tags) {
            tagDetails += PREFIX_DELIMITER + PREFIX_TAG + " " + tag.toSaveString() + " ";
        }
        noteDetails += PREFIX_DELIMITER + PREFIX_TITLE + " " + this.title + " "
                    + PREFIX_DELIMITER + PREFIX_PIN + " " + this.isPinned + " "
                    + tagDetails + LS;

        return noteDetails;
    }
}
