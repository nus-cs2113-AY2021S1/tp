package seedu.eduke8.note;

import seedu.eduke8.common.Displayable;

public class Note implements Displayable {
    private String description;
    private String noteText;
    private boolean wasShown;

    public Note(String description, String noteText) {
        assert (description != null);
        assert (noteText != null);
        this.description = description;
        this.noteText = noteText;
        wasShown = false;
    }

    /**
     * Returns the description (which is the title) of the note.
     *
     * @return String description.
     */
    @Override
    public String getDescription() {
        markAsShown();
        return this.description;
    }

    /**
     * Marks the note as shown.
     */
    @Override
    public void markAsShown() {
        wasShown = true;
    }

    /**
     * Returns a boolean variable indicating if the note was shown to the user.
     *
     * @return boolean wasShown.
     */
    @Override
    public boolean wasShown() {
        return this.wasShown;
    }

    /**
     * Returns the text in inputted into the note by the user.
     *
     * @return String noteText.
     */
    public String getNoteText() {
        return this.noteText;
    }
}
