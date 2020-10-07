package seedu.duke.question;

import seedu.duke.OptionInterface;

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
