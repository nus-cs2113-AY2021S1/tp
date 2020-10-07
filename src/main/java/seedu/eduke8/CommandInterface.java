package seedu.eduke8;

import seedu.eduke8.storage.StorageInterface;

public interface CommandInterface {
    void execute(TopicListInterface topics, StorageInterface storage);

    boolean isExit();

}
