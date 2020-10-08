package seedu.eduke8.storage;

import seedu.eduke8.topic.TopicInterface;
import java.util.ArrayList;

public interface StorageInterface {
    void save(ArrayList<TopicInterface> topics);

    ArrayList<TopicInterface> load();

}
