package seedu.eduke8.topic;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;

import java.util.ArrayList;

public class TopicList implements DisplayableList {
    private ArrayList<Displayable> topics;

    public TopicList(ArrayList<Displayable> topics) {
        this.topics = topics;
    }

    @Override
    public ArrayList<Displayable> getInnerList() {
        return topics;
    }

    @Override
    public void add(Displayable topic) {
        topics.add(topic);
    }

    @Override
    public void delete(int index) {
        topics.remove(index);
    }

    @Override
    public Displayable find(String topicName) {
        for (Displayable topic : topics) {
            if (topicName.equals(topic.getDescription())) {
                return topic;
            }
        }
        return null;
    }

    public int getCount() {
        return topics.size();
    }
}
