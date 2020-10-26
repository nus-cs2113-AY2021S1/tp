package seedu.eduke8.command;

import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.stats.Stats;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

public class StatsCommand extends Command {
    private TopicList topicList;

    public StatsCommand(TopicList topicList) {
        super();
        assert topicList != null;

        this.topicList = topicList;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        new Stats(topicList).showStatsToUser(ui);
    }
}
