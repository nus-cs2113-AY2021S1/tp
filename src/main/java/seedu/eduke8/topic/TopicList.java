package seedu.eduke8.topic;

import java.util.ArrayList;

public class TopicList implements TopicListInterface {
    private ArrayList<TopicInterface> allTopics;

    public TopicList(ArrayList<TopicInterface> loadedTopics) {
        allTopics = loadedTopics;
    }

    @Override
    public void showTopics() {
        System.out.println("These are the available topics:");
        for (int i = 0; i < allTopics.size(); i++) {
            System.out.println(allTopics.get(i).getTopic());
        }
    }

    @Override
    public void addTopic(TopicInterface topic) {
        allTopics.add(topic);
    }

    @Override
    public void deleteTopic(int index) {
        allTopics.remove(index);
    }

    public TopicInterface findTopic(String topicName) {
        for (TopicInterface topic : allTopics) {
            if (topicName.equals(topic.getTopic())) {
                return topic;
            }
        }
        return null;
    }

    @Override
    public int getTopicCount() {
        return allTopics.size();
    }
}
