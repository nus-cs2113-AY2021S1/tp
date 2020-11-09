package seedu.eduke8.explanation;

import seedu.eduke8.common.Displayable;

/**
 * Represents an explanation of a certain question.
 */
public class Explanation implements Displayable {
    private String description;
    private boolean wasShown;

    public Explanation(String description) {
        this.description = description;
        wasShown = false;
    }

    /**
     * Returns the explanation of the question.
     *
     * @return Description of Explanation.
     */
    @Override
    public String getDescription() {
        markAsShown();
        return description;
    }

    /**
     * Marks explanation as shown.
     */
    @Override
    public void markAsShown() {
        wasShown = true;
    }

    /**
     * Returns a boolean variable indicating if the explanation was shown before.
     *
     * @return Indication of whether the explanation was shown before.
     */
    @Override
    public boolean wasShown() {
        return this.wasShown;
    }
}
