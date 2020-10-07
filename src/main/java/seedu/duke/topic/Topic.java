package seedu.duke.topic;

import seedu.duke.QuestionListInterface;

class Topic implements TopicInterface {

    private String topicName;
    private QuestionListInterface questionList;


    public Topic(String topicName, QuestionListInterface questionList) {
        this.topicName = topicName;
        this.questionList = questionList;
    }

    /**
     * Returns the topic of the .json file, assumed to
     * be under the field "
     *
     * @return String containing the topic name
     */
    public String getTopic() {
        return topicName;
    }

    public QuestionListInterface getQuestionList() {
        return questionList;
    }
}
