package seedu.eduke8.topic;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;
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
    public Displayable find(String topicName) throws Eduke8Exception {
        for (Displayable topic : topics) {
            if (topicName.equalsIgnoreCase(topic.getDescription())) {
                return topic;
            }
        }
        throw new Eduke8Exception("No such topic exists, did you spell it correctly?");
    }

    public int getCount() {
        return topics.size();
    }
}
