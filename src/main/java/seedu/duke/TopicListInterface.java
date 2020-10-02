package seedu.duke;

import java.util.ArrayList;

public interface TopicListInterface {
    public ArrayList<TopicInterface> getTopics();
    public void showTopics();
    public void addTopic(TopicInterface topic);
    public void deleteTopic(TopicInterface topic);
    public int getTopicCount();
}
