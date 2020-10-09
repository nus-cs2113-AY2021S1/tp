package seedu.eduke8;

import seedu.eduke8.storage.Storage;
import seedu.eduke8.topic.TopicList;


public interface Command {
    void execute(TopicList topics, Storage storage);

    boolean isExit();

}
