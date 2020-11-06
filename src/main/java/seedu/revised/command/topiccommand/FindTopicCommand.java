package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.exception.topicexception.InvalidTopicException;
import seedu.revised.list.TopicList;
import seedu.revised.command.taskcommand.FindTaskCommand;
import seedu.revised.ui.Ui;

public class FindTopicCommand extends TopicCommand {
    private String fullcommand;

    public FindTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public void execute(Subject subject) throws InvalidTopicException {
        TopicList topicList = subject.getTopics();
        String[] message = this.fullcommand.split("\\s+", 2);
        if (message.length <= 1 || message[1].isEmpty()) {  // empty argument
            throw new InvalidTopicException(Ui.INVALID_TOPIC_EXCEPTION);
        }

        Ui.printFindTopic(topicList, message[1].strip());
        FindTaskCommand findTaskCommand = new FindTaskCommand(this.fullcommand);
        findTaskCommand.execute(subject.getTasks());
    }

    /**
     * Checks whether the the user exits the program.
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
