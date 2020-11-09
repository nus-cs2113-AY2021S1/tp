package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.exception.FailedParseException;
import seedu.revised.ui.Ui;

public class SorryTopicCommand extends TopicCommand {

    public void execute(Subject subject) throws FailedParseException {
        throw new FailedParseException(Ui.FAILED_PARSE_EXCEPTION);
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
