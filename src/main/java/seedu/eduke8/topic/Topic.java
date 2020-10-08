package seedu.eduke8.topic;

import seedu.eduke8.question.QuestionListInterface;

public class Topic implements TopicInterface {

    private String topicName;
    private QuestionListInterface allQuestions;


    public Topic(String topicName, QuestionListInterface allQuestions) {
        this.topicName = topicName;
        this.allQuestions = allQuestions;
    }

    public String getTopic() {
        return topicName;
    }

    public QuestionListInterface getQuestionList() {
        return allQuestions;
    }
}
