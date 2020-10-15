package seedu.eduke8.hint;

import seedu.eduke8.common.Displayable;

public class Hint implements Displayable {
    private String description;
    private boolean wasShown;

    public Hint(String description) {
        this.description = description;
        wasShown = false;
    }

    @Override
    public String getDescription() {
        wasShown = true;
        return description;
    }

    @Override
    public boolean wasShown() {
        return this.wasShown;
    }
}
