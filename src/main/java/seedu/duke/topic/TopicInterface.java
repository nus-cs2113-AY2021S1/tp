package seedu.duke.topic;

import seedu.duke.QuestionListInterface;

import seedu.duke.question.QuestionListInterface;

public interface TopicInterface {
    String getTopic();

    QuestionListInterface getQuestionList();

}