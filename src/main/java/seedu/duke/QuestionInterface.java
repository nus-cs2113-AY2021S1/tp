package seedu.duke;

public interface QuestionInterface {
    public String getQuestionDescription();
    public void showOptions();
    public void showHint();
    public boolean hintWasShown();
    public void setHintShown();
    public boolean wasAnsweredCorrectly();
    public void setAsAnsweredCorrectly();
    public boolean wasAttempted();
    public void setAsAttempted();

}
