package seedu.duke.question;

import seedu.duke.HintInterface;
import seedu.duke.OptionInterface;
import seedu.duke.OptionListInterface;

import java.util.ArrayList;

public interface QuestionInterface {

    // might want to change all the interface classes to just the classes themselves

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
