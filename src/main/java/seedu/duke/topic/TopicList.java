package seedu.duke.topic;

import java.util.ArrayList;

class TopicList implements TopicListInterface {
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

    @Override
    public int getTopicCount() {
        return allTopics.size();
    }
}
