package seedu.duke;

import java.util.ArrayList;

public interface StorageInterface {
    void save(ArrayList<TopicInterface> topics);

    ArrayList<TopicInterface> load();

}
