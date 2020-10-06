package seedu.duke.question;

import seedu.duke.HintInterface;
import seedu.duke.OptionInterface;
import seedu.duke.OptionListInterface;

import java.util.ArrayList;

public class Question implements QuestionInterface {

    // might want to change all the interface classes to just the classes themselves

    private String description;
    private OptionListInterface options;
    private HintInterface hint;
    private boolean wasHintShown;
    private boolean wasAnsweredCorrectly;
    private boolean wasAttempted;

    public Question(String description, OptionListInterface options, HintInterface hint) {
        this.description = description;
        this.options = options;
        this.hint = hint;
        wasHintShown = false;
        wasAnsweredCorrectly = false;
        wasAttempted = false;
    }

    /**
     * Returns the description of the question.
     *
     * @return Question information.
     */
    @Override
    public String getQuestionDescription() {
        return description;
    }

    @Override
    public ArrayList<OptionInterface> getOptions() {
        return options.getOptionList();
    }

    @Override
    public String getHint() {
        return hint.getHintDescription();
    }

    @Override
    public boolean hintWasShown() {
        return wasHintShown;
    }

    @Override
    public void setHintShown() {
        wasHintShown = true;
    }

    @Override
    public boolean wasAnsweredCorrectly() {
        return wasAnsweredCorrectly;
    }

    @Override
    public void setAsAnsweredCorrectly() {
        wasAnsweredCorrectly = true;
    }

    @Override
    public boolean wasAttempted() {
        return wasAttempted;
    }

    @Override
    public void setAsAttempted() {
        wasAttempted = true;
    }
}
