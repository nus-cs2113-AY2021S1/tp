package seedu.duke;

public interface CommandInterface {
    void execute(TopicListInterface topics, StorageInterface storage);

    boolean isExit();

}
