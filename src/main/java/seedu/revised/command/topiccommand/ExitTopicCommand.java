package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.topicexception.InvalidTopicException;
import seedu.revised.exception.topicexception.NoTopicException;
import seedu.revised.exception.topicexception.RepeatedTopicException;

public class ExitTopicCommand extends TopicCommand {
    @Override
    public void execute(Subject subject) throws NoTopicException, InvalidTopicException,
            FailedParseException, RepeatedTopicException {
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return true;
    }
}
