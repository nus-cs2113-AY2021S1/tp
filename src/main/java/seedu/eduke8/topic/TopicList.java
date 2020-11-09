package seedu.eduke8.topic;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.ui.Ui;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_TOPIC_DOES_NOT_EXIST;

import java.util.ArrayList;
import java.util.logging.Logger;

public class TopicList implements DisplayableList {
    private ArrayList<Displayable> topics;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public TopicList(ArrayList<Displayable> topics) {
        assert topics.size() > 0;
        this.topics = topics;
    }

    /**
     * Prints out description from Topic objects in TopicList
     *
     * @param ui
     */
    public void showTopics(Ui ui) {
        ui.printTopicList(topics);
    }


    /**
     * Returns boolean result to indicate if the Topic object exists.
     *
     * @param topicName
     * @return boolean result.
     */
    public boolean doesTopicExist(String topicName) {
        boolean result = false;
        for (int i = 0; i < topics.size(); i++) {
            if (topics.get(i).getDescription().equalsIgnoreCase(topicName)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Returns all Topic objects in the TopicList.
     *
     * @return topics.
     */
    @Override
    public ArrayList<Displayable> getInnerList() {
        return topics;
    }

    /**
     * Returns a Topic object that has the same description as the input.
     *
     * @param topicName which is the description of the Topic object to be found.
     * @return Topic topic if the topic is found, null if the topic is not found.
     * @throws Eduke8Exception
     */
    @Override
    public Displayable find(String topicName) throws Eduke8Exception {
        for (Displayable topic : topics) {
            if (topicName.equalsIgnoreCase(topic.getDescription())) {
                return topic;
            }
        }
        throw new Eduke8Exception(ERROR_TOPIC_DOES_NOT_EXIST);
    }

    /**
     * Returns the Topic object which has the same index in the TopicList as the index
     * provided in the input.
     *
     * @param i the index of the Topic object that is to be retrieved.
     * @return Topic topic.
     */
    public Topic get(int i) {
        return (Topic) topics.get(i);
    }

    /**
     * Returns the number of Topic objects currently in the TopicList.
     *
     * @return int number of Topic objects.
     */
    public int getCount() {
        return topics.size();
    }
}
