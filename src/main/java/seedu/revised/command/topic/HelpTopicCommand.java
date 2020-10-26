package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.topic.TopicCommand;
import seedu.revised.ui.Ui;

public class HelpTopicCommand extends TopicCommand {
    public void execute(Subject subject) {
        Ui.printTopicHelp();
    }

    public boolean isExit() {
        return false;
    }
}


