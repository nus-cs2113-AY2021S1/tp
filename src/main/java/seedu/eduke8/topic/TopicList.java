package seedu.eduke8.topic;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;

import java.util.ArrayList;
import seedu.eduke8.ui.Ui;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TopicList implements DisplayableList {
    private ArrayList<Displayable> topics;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public TopicList(ArrayList<Displayable> topics) {
        assert topics.size() > 0;
        this.topics = topics;
    }

    public void showTopics(Ui ui) {
        ui.printTopicList(topics);
    }

    @Override
    public ArrayList<Displayable> getInnerList() {
        return topics;
    }

    @Override
    public void add(Displayable topic) {
        String message = "Topic " + topic.getDescription() + " added";
        topics.add(topic);
        LOGGER.log(Level.INFO, message);
    }

    @Override
    public void delete(int index) {
        String message = "Topic " + topics.get(index).getDescription() + " deleted";
        topics.remove(index);
        LOGGER.log(Level.INFO, message);
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
