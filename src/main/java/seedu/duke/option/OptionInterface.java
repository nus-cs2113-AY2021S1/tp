package seedu.duke.option;

public interface OptionInterface {
    String getOptionDescription();

    boolean isCorrectAnswer();

    void markAsCorrectAnswer();
}
