package seedu.eduke8.explanation;

import seedu.eduke8.common.Displayable;

public class Explanation implements Displayable {
    private String description;
    private boolean wasShown;

    public Explanation(String description) {
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
