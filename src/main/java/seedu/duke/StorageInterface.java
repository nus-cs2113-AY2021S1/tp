package seedu.duke;

import java.util.ArrayList;

public interface StorageInterface {
    public void save(ArrayList<TopicInterface> topics);
    public ArrayList<TopicInterface> load();
}
