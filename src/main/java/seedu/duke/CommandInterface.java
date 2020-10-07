package seedu.duke;

import seedu.duke.storage.StorageInterface;
import seedu.duke.topic.TopicListInterface;

public interface CommandInterface {
    void execute(TopicListInterface topics, StorageInterface storage);

    boolean isExit();

}
