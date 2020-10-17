package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.Command;
import seedu.revised.exception.card.InvalidTopicCommand;
import seedu.revised.exception.card.NoSubjectException;
import seedu.revised.exception.card.NoTopicException;
import seedu.revised.exception.card.RepeatedSubjectException;

public class TopicCommand extends Command {
    public Topic execute(Subject subject) throws NoSubjectException, RepeatedSubjectException,
            NoTopicException, InvalidTopicCommand {
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
