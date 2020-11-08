package seedu.eduke8.option;

import seedu.eduke8.common.Displayable;

/**
 * Represents an option of a certain question.
 */
public class Option implements Displayable {
    private String description;
    private boolean isCorrectAnswer;
    private boolean wasShown;

    public Option(String description) {
        assert description != null;

        this.description = description;
        isCorrectAnswer = false;
        wasShown = false;
    }

    /**
     * Returns the description of the option.
     *
     * @return Option description.
     */
    @Override
    public String getDescription() {
        markAsShown();
        return description;
    }

    /**
     * Marks this option as shown.
     */
    @Override
    public void markAsShown() {
        wasShown = true;
    }

    /**
     * Returns a boolean variable indicating if the option was shown before.
     *
     * @return Indication of whether the option was shown before.
     */
    @Override
    public boolean wasShown() {
        return this.wasShown;
    }

    /**
     * Returns a boolean variable indicating if the option is the correct answer.
     *
     * @return Indication of whether the option is the correct answer.
     */
    public boolean isCorrectAnswer() {
        return this.isCorrectAnswer;
    }

    /**
     * Marks this option as the correct answer.
     */
    public void markAsCorrectAnswer() {
        isCorrectAnswer = true;
    }
}
