package seedu.eduke8.question;

import seedu.eduke8.OptionInterface;

import java.util.ArrayList;

public interface QuestionInterface {

    String getQuestionDescription();

    ArrayList<OptionInterface> getOptions();

    String getHint();

    boolean hintWasShown();

    void setHintShown();

    boolean wasAnsweredCorrectly();

    void setAsAnsweredCorrectly();

    boolean wasAttempted();

    void setAsAttempted();

}
