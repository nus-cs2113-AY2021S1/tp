package seedu.eduke8.option;

public interface OptionInterface {
    String getOptionDescription();

    boolean isCorrectAnswer();

    void markAsCorrectAnswer();
}
