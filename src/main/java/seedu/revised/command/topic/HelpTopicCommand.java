package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.topic.TopicCommand;
import seedu.revised.ui.Ui;

public class HelpTopicCommand extends TopicCommand {
     public Topic execute(Subject subject) {
         Ui.printTopicHelp();
         return null;
     }

     public boolean isExit() {
            return false;
        }
}


