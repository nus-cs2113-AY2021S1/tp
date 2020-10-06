package seedu.duke.question;

import seedu.duke.hint.HintInterface;
import seedu.duke.OptionInterface;
import seedu.duke.OptionListInterface;

import java.util.ArrayList;

/**
 * Represents a question of a certain topic. A <code>Question</code> object is represented by
 * its description, options and hint.
 */
public class Question implements QuestionInterface {
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

    /**
     * Returns the multiple options of the question.
     *
     * @return An arraylist of options.
     */
    @Override
    public ArrayList<OptionInterface> getOptions() {
        return options.getOptionList();
    }

    /**
     * Returns the description of the hint tied to the question.
     *
     * @return Details of the hint.
     */
    @Override
    public String getHint() {
        return hint.getHintDescription();
    }

    /**
     * Returns the description of the question.
     *
     * @return Question information.
     */
    @Override
    public boolean hintWasShown() {
        return wasHintShown;
    }


    @Override
    public void setHintShown() {
        wasHintShown = true;
    }

    /**
     * Returns a boolean variable indicating if the question was answered correctly.
     *
     * @return True if question was answered correctly, false otherwise.
     */
    @Override
    public boolean wasAnsweredCorrectly() {
        return wasAnsweredCorrectly;
    }

    @Override
    public void setAsAnsweredCorrectly() {
        wasAnsweredCorrectly = true;
    }

    /**
     * Returns a boolean variable indicating if the question was already attempted before.
     *
     * @return Indication of whether the question was attempted before or not.
     */
    @Override
    public boolean wasAttempted() {
        return wasAttempted;
    }


    @Override
    public void setAsAttempted() {
        wasAttempted = true;
    }
}
