package seedu.duke;

public interface QuestionInterface {
    String getQuestionDescription();

    void showOptions();

    void showHint();

    boolean hintWasShown();

    void setHintShown();

    boolean wasAnsweredCorrectly();

    void setAsAnsweredCorrectly();

    boolean wasAttempted();

    void setAsAttempted();

}
