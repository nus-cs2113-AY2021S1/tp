package seedu.eduke8.storage;

import seedu.eduke8.TopicInterface;

import java.util.ArrayList;

public interface StorageInterface {
    void save(ArrayList<TopicInterface> topics);

    ArrayList<TopicInterface> load();

}
