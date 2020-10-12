package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

public class TopicsCommand extends Command {
    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        ((TopicList) displayableList).showTopics();
    }
}
