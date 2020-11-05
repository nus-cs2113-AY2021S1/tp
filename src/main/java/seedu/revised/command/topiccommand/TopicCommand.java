package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.command.Command;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcardexception.NoFlashcardException;
import seedu.revised.exception.topicexception.InvalidTopicException;
import seedu.revised.exception.topicexception.NoTopicException;
import seedu.revised.exception.topicexception.RepeatedTopicException;

public abstract class TopicCommand extends Command {
    public abstract void execute(Subject subject) throws NoTopicException, InvalidTopicException,
            FailedParseException, RepeatedTopicException, NoFlashcardException;

    public abstract boolean isExit();
}
