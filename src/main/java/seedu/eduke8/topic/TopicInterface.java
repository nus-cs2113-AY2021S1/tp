package seedu.eduke8.topic;

import seedu.eduke8.question.QuestionListInterface;

public interface TopicInterface {
    String getTopic();

    QuestionListInterface getQuestionList();
}