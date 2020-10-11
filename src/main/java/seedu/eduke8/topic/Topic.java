package seedu.eduke8.topic;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.TopicQuestionList;

public class Topic implements Displayable {
    private String description;
    private TopicQuestionList questionList;
    private boolean wasShown;

    public Topic(String description, TopicQuestionList topicQuestionList) {
        this.description = description;
        this.questionList = topicQuestionList;
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

    public TopicQuestionList getQuestionList() {
        return questionList;
    }
}
