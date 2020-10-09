package seedu.duke.command.topiccommand;

import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.util.List;

public class ListTopicCommand extends TopicCommand {

    public Topic execute(Subject subject) {
        Ui.printTopicList(subject);
        Ui.printTaskList(subject);
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
