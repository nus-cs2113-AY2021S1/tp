package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.ui.Ui;

public class HelpTopicCommand extends TopicCommand {
    public void execute(Subject subject) {
        Ui.printTopicHelp();
    }

    public boolean isExit() {
        return false;
    }
}


