package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;

public class ExitTopicCommand extends TopicCommand {

    /**
     * Does nothing, needed because it inherits from an abstract class.
     *
     * @param subject The subject that the user is currently looking at
     */
    @Override
    public void execute(Subject subject)  {
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
