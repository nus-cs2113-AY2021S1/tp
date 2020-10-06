package seedu.duke;

import seedu.duke.storage.StorageInterface;

public interface CommandInterface {
    void execute(TopicListInterface topics, StorageInterface storage);

    boolean isExit();

}
