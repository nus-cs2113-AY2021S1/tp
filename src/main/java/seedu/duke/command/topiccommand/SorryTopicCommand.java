package seedu.duke.command.topiccommand;

import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.ui.Ui;

public class SorryTopicCommand extends TopicCommand {

    public Topic execute(Subject subject) {
        Ui.printError();
        return null;
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
