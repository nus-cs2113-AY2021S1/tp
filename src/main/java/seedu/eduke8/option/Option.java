package seedu.eduke8.option;

public class Option implements OptionInterface {
    private String optionDescription;
    private boolean isCorrectAnswer;

    public Option(String description) {
        this.optionDescription = description;
        this.isCorrectAnswer = false;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void markAsCorrectAnswer() {
        this.isCorrectAnswer = true;
    }
}
