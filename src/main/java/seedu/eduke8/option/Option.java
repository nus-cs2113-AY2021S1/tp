package seedu.eduke8.option;

import seedu.eduke8.common.Displayable;

public class Option implements Displayable {
    private String description;
    private boolean isCorrectAnswer;
    private boolean wasShown;

    public Option(String description) {
        this.description = description;
        isCorrectAnswer = false;
        wasShown = false;
    }

    @Override
    public String getDescription() {
        wasShown = true;
        return description;
    }

    @Override
    public boolean wasShown() {
        return wasShown;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void markAsCorrectAnswer() {
        this.isCorrectAnswer = true;
    }
}
