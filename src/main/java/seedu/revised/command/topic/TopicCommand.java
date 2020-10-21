package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.Command;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.exception.topic.RepeatedTopicException;

public class TopicCommand extends Command {
    public Topic execute(Subject subject) throws NoSubjectException, RepeatedSubjectException,
            NoTopicException, InvalidTopicException, FailedParseException, RepeatedTopicException {
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
