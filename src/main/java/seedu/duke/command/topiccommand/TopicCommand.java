package seedu.duke.command.topiccommand;

import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.command.Command;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.exception.RepeatedSubjectException;

public class TopicCommand extends Command {
    public Topic execute(Subject subject) throws NoSubjectException, RepeatedSubjectException {
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
