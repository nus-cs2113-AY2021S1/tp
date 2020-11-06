package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.ui.Ui;

public class ListTopicCommand extends TopicCommand {

    public void execute(Subject subject) {
        Ui.printTopicList(subject);
        Ui.printTaskList(subject);
    }

    public boolean isExit() {
        return false;
    }
}
