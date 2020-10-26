package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.exception.topic.RepeatedTopicException;

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
