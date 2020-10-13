package seedu.eduke8.question;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.OptionList;

/**
 * Represents a question of a certain topic. A <code>Question</code> object is represented by
 * its description, options and hint.
 */
public class Question implements Displayable {
    private String description;
    private OptionList optionList;
    private Hint hint;
    private boolean wasAnsweredCorrectly;
    private boolean wasShown;

    public Question(String description, OptionList optionList, Hint hint) {
        // assert description != null;     // for another pull request.
        this.description = description;

        // assert optionList != null;       // for another pull request.
        this.optionList = optionList;

        this.hint = hint;
        wasAnsweredCorrectly = false;
        wasShown = false;
    }

    /**
     * Returns the description of the question.
     *
     * @return Question information.
     */
    @Override
    public String getDescription() {
        wasShown = true;
        return description;
    }

    /**
     * Returns a boolean variable indicating if the question was already attempted before.
     *
     * @return Indication of whether the question was attempted before or not.
     */
    @Override
    public boolean wasShown() {
        return wasShown;
    }


    /**
     * Returns the multiple options of the question.
     *
     * @return An  arraylist of options pertaining to this question.
     */
    public OptionList getOptionList() {
        return optionList;
    }

    /**
     * Returns the hint tied to the question.
     *
     * @return Details of the hint.
     */
    public Hint getHint() {
        return hint;
    }

    /**
     * Returns a boolean variable indicating if the user had requested for a hint for this question.
     *
     * @return Indication of whether the hint for this question was shown before,
     */
    public boolean wasHintShown() {
        return hint.wasShown();
    }

    /**
     * Returns a boolean variable indicating if the question was answered correctly.
     *
     * @return True if question was answered correctly, false otherwise.
     */
    public boolean wasAnsweredCorrectly() {
        return wasAnsweredCorrectly;
    }

    /**
     * Marks this question as answered correctly.
     */
    public void markAsAnsweredCorrectly() {
        wasAnsweredCorrectly = true;
    }
}
