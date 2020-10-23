package seedu.notus.data.notebook;

import seedu.notus.data.tag.Tag;
import seedu.notus.data.tag.TaggableObject;

import java.util.ArrayList;

//@@author Narzyl
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

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getContent() {
        return content;
    }

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
        return (isPinned ? "Y" : "N");
    }

    public void togglePinned() {
        isPinned = !isPinned;
    }

    public void setPinned(Boolean pinned) {
        isPinned = pinned;
    }
}
