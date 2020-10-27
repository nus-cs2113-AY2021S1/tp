package seedu.eduke8.note;

import seedu.eduke8.common.Displayable;

public class Note implements Displayable {
    private String description;
    private boolean wasShown;

    public Note(String description) {
        this.description = description;
        wasShown = false;
    }

    @Override
    public String getDescription() {
        markAsShown();
        return this.description;
    }

    @Override
    public void markAsShown() {
        wasShown = true;
    }

    @Override
    public boolean wasShown() {
        return this.wasShown;
    }
}
