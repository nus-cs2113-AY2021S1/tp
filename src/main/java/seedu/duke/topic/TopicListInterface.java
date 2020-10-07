package seedu.duke.topic;

import java.util.ArrayList;

public interface TopicListInterface {
    void showTopics();

    void addTopic(TopicInterface topic);

    void deleteTopic(int index);

    int getTopicCount();

}