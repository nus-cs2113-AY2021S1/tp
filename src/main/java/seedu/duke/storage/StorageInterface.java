package seedu.duke.storage;

import seedu.duke.topic.TopicInterface;

import java.util.ArrayList;

public interface StorageInterface {
    void save(ArrayList<TopicInterface> topics);

    ArrayList<TopicInterface> load();

}
