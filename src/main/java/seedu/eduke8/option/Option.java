package seedu.eduke8.option;

import seedu.eduke8.common.Displayable;

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

    public boolean isCorrectAnswer() {
        return this.isCorrectAnswer;
    }

    public void markAsCorrectAnswer() {
        isCorrectAnswer = true;
    }
}
