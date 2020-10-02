package seedu.duke;

public interface CommandInterface {
    public void execute(TopicListInterface topics, StorageInterface storage);
    public boolean isExit();
}
