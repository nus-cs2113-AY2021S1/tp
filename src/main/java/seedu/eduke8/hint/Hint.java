package seedu.eduke8.hint;

import seedu.eduke8.common.Displayable;

public class Hint implements Displayable {
    private String description;
    private boolean wasShown;

    public Hint(String description) {
        this.description = description;
        wasShown = false;
    }

    /**
     * Returns the description of the question.
     *
     * @return Question information.
     */
    @Override
    public String getDescription() {
        markAsShown();
        return description;
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
