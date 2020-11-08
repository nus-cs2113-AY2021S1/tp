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
     * Returns the description of the hint.
     *
     * @return Hint information.
     */
    @Override
    public String getDescription() {
        markAsShown();
        return description;
    }

    /**
     * Marks this hint as being used by the user when attempting a question.
     */
    @Override
    public void markAsShown() {
        wasShown = true;
    }

    /**
     * Returns a boolean variable indicating if the hint was shown to the user before.
     *
     * @return Indication of whether the hint was shown before or not.
     */
    @Override
    public boolean wasShown() {
        return this.wasShown;
    }
}
