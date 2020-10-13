package seedu.eduke8.topic;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.QuestionList;

public class Topic implements Displayable {
    private String description;
    private QuestionList questionList;
    private boolean wasShown;

    public Topic(String description, QuestionList questionList) {
        this.description = description;
        this.questionList = questionList;
        wasShown = false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean wasShown() {
        return wasShown;
    }

    public QuestionList getQuestionList() {
        return questionList;
    }
}
