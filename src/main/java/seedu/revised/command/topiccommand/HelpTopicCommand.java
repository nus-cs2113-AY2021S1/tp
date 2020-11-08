package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.ui.Ui;

public class HelpTopicCommand extends TopicCommand {
    /**
     * Prints the list of subject level commands.
     *
     * @param subject Subject the user is currently looking at
     */
    public void execute(Subject subject) {
        Ui.printTopicHelp();
    }

    /**
     * Checks whether the the user exits the subject.
     *
     * @return <code>true</code> If user exits the subject
     */
    public boolean isExit() {
        return false;
    }
}


