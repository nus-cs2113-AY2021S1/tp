package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.Command;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.exception.topic.RepeatedTopicException;

public abstract class TopicCommand extends Command {
    public abstract void execute(Subject subject) throws NoTopicException, InvalidTopicException,
            FailedParseException, RepeatedTopicException, NoFlashcardException;

    public boolean isExit() {
        return false;
    }
}
