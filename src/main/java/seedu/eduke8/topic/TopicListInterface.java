package seedu.eduke8.topic;

public interface TopicListInterface {
    void showTopics();

    void addTopic(TopicInterface topic);

    void deleteTopic(int index);

    int getTopicCount();

}