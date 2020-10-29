package seedu.eduke8.question;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.explanation.Explanation;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.OptionList;

/**
 * Represents a question of a certain topic. A <code>Question</code> object is represented by
 * its description, options, hint and explanation.
 */
public class Question implements Displayable {
    private String description;
    private OptionList optionList;
    private Hint hint;
    private Explanation explanation;
    private boolean wasAnsweredCorrectly;
    private boolean wasShown;
    private boolean isBookmarked;


    public Question(String description, OptionList optionList, Hint hint, Explanation explanation) {
        assert description != null;     //A question must have its description
        this.description = description;

        assert optionList != null;       //A question must have some options
        this.optionList = optionList;

        this.explanation = explanation;
        this.hint = hint;
        wasAnsweredCorrectly = false;
        wasShown = false;
        isBookmarked = false;
    }

    /**
     * Returns the description of the question.
     *
     * @return Question information.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Marks this question as shown, in other words, attempted by user.
     */
    @Override
    public void markAsShown() {
        wasShown = true;
    }

    /**
     * Returns a boolean variable indicating if the question was already attempted before.
     *
     * @return Indication of whether the question was attempted before or not.
     */
    @Override
    public boolean wasShown() {
        return this.wasShown;
    }

    /**
     * Returns the multiple options of the question.
     *
     * @return An arraylist of options pertaining to this question.
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
     * Returns the explanation tied to the question.
     *
     * @return Details of the explanation.
     */
    public Explanation getExplanation() {
        return explanation;
    }

    /**
     * Returns a boolean variable indicating if the user had requested for a hint for this question.
     *
     * @return Indication of whether the hint for this question was shown before.
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

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public boolean markAsBookmarked() {
        return isBookmarked = true;
    }
}
