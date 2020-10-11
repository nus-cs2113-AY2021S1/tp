package seedu.duke.command.topiccommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.card.Topic;
import seedu.duke.card.TopicList;
import seedu.duke.ui.Ui;

public class ListTopicCommand extends TopicCommand {

    public Topic execute(Subject subject) {
        Ui.printTopicList(subject);
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
